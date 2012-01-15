package de.robotniko.reverseLookup;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.log4j.Logger;

import de.robotniko.reverseLookup.api.IReverseLookupFinishedListener;
import de.robotniko.reverseLookup.api.ReverseLookupFacade;
import de.robotniko.reverseLookup.api.ReverseLookupRequest;
import de.robotniko.reverseLookup.api.ReverseLookupResponse;
import de.robotniko.reverseLookup.exceptions.ReverseLookupException;

public class ReverseLookupAsyncThread extends Thread {

	private final static Logger LOG = Logger.getLogger(ReverseLookupAsyncThread.class);

	private PriorityQueue<ReverseLookupRequest> requestQueue;
	private boolean terminate = false;
	private boolean terminated = false;
	private boolean threadSuspended = false;
	private boolean quitOnDone;
	private int numLookups = 0;
	private int numDone = 0;
	private List<IReverseLookupFinishedListener> finishedListenerList;

	public ReverseLookupAsyncThread(boolean quitOnDone) {
		this.requestQueue = new PriorityQueue<ReverseLookupRequest>();
		this.finishedListenerList = new ArrayList<IReverseLookupFinishedListener>();
		this.quitOnDone = quitOnDone;
	}

	public synchronized void addRequest(final List<ReverseLookupRequest> requests, final IReverseLookupFinishedListener finishedListener) {
		finishedListenerList.add(finishedListener);
		requestQueue.addAll(requests);
		numLookups += requests.size();
		if (this.isAlive()) {
			this.resumeLookup();
		}
	}

	public synchronized void addRequest(final ReverseLookupRequest request, final IReverseLookupFinishedListener finishedListener) {
		finishedListenerList.add(finishedListener);
		if (!requestQueue.contains(request)) {
			requestQueue.add(request);
		}
		numLookups++;
		if (this.isAlive()) {
			this.resumeLookup();
		}
	}

	public synchronized ReverseLookupRequest getNextRequest() {
		return requestQueue.poll();
	}

	public void run() {
		while (!terminate) {
			try {
				if (requestQueue.size() == 0 && quitOnDone) {
					break;
				}

				synchronized(this) {
					while (!terminate && (threadSuspended || requestQueue.size() == 0)) {
						// TODO notify: finished lookup
						for (IReverseLookupFinishedListener l: finishedListenerList) {
							l.finished();
						}
						numDone = 0;
						numLookups = 0;
						finishedListenerList.clear();
						wait();
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			if (!terminate) {
				final ReverseLookupRequest nextRequest = getNextRequest();
				try {
					List<ReverseLookupResponse> response = ReverseLookupFacade.getReverseLookupService().blockingLookup(nextRequest);
					numDone++;
					if (nextRequest.getListener() != null) {
						nextRequest.getListener().lookupResponse(response, (int)(((float)numDone / (float)numLookups) * 100.0f));
					}
				} catch (ReverseLookupException e) {
					LOG.warn("Exception while looking up " + nextRequest.getInternationalPhoneNumber() + ": " + e.getMessage());
				}

				// TODO maybe sleep here?
			}
		}
		terminated = true;
	}

	public synchronized void terminate(){
		terminate = true;
		resumeLookup();
	}

	public synchronized boolean isTerminated(){
		return terminated;
	}

	public synchronized void suspendLookup(){
		LOG.info("suspending lookup thread");
		threadSuspended = true;
	}

	public synchronized void resumeLookup(){
		threadSuspended = false;
		notify();
		LOG.info("resuming lookup thread again");
	}

	public synchronized boolean isSuspended(){
		return threadSuspended;
	}
}
