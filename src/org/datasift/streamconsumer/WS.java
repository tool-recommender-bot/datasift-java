/**
 * 
 */
package org.datasift.streamconsumer;

import java.net.URISyntaxException;

import org.datasift.*;

import de.roderick.weberknecht.WebSocketException;

/**
 * @author stuart
 * 
 */
public class WS extends StreamConsumer {
	/**
	 * This is the thread that consumes the HTTP stream.
	 */
	private WSThread _thread = null;
	
	/**
	 * Constructor.
	 * 
	 * @param User
	 *            user
	 * @param ArrayList<String>
	 *            hashes
	 * @param IMultiStreamConsumerEvents
	 *            eventHandler
	 * @throws EInvalidData
	 * @throws ECompileFailed
	 * @throws EAccessDenied
	 * @throws EAPIError 
	 * @throws URISyntaxException 
	 * @throws WebSocketException 
	 */
	public WS(User user,
			IMultiStreamConsumerEvents eventHandler) throws EInvalidData,
			ECompileFailed, EAccessDenied, EAPIError {
		super(user, eventHandler);
		try {
			_thread = new WSThread(this, user);
		} catch (WebSocketException e) {
			throw new EAPIError(e.getMessage());
		} catch (URISyntaxException e) {
			throw new EAPIError(e.getMessage());
		}
	}

	public void setAutoReconnect(boolean auto_reconnect) {
		_thread.setAutoReconnect(auto_reconnect);
	}
	
	@Override
	public void subscribe(String hash) throws EAPIError {
		_thread.subscribe(hash);
	}
	
	@Override
	public void unsubscribe(String hash) throws EAPIError {
		_thread.unsubscribe(hash);
	}
	
	public boolean isRunning() {
		if (_thread == null) {
			return false;
		}
		return _thread.isAlive();
	}

	@Override
	protected void onStart(boolean auto_reconnect) {
		setAutoReconnect(auto_reconnect);
		if (!isRunning()) {
			_state = StreamConsumer.STATE_RUNNING;
			_thread.start();
		}
	}
	
	protected void restart() {
		_state = StreamConsumer.STATE_RESTARTING;
	}
	
	protected void onRestarted() {
		_state = StreamConsumer.STATE_RUNNING;
	}
}
