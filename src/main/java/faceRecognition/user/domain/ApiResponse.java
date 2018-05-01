package faceRecognition.user.domain;

public class ApiResponse {

	/**
	 * The message that should be showed to the api consumer
	 */
	private String message;

	/**
	 * The response code that should be sent to the api consumer, this is Enum where
	 * we have some constants like NO_CONTENT, PAGE_NOT_FOUND, OK
	 */
	private ResponseCode responseCode;

	/**
	 * The result that should be sent to the api consumer, this can include any
	 * object for example List<Address>, Address, ...
	 */
	private Object result;

	public ApiResponse() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
