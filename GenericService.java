package com.wowment.ws;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.wowment.model.message.Message;



/**
 * 
 * @author Silxo
 *
 */
@RestController
public abstract class GenericService {

	private static final String INTERNAL_ERROR = "Internal Server Error";
	private static final String UNAUTHORIZED = "Unauthorized access";
	private static final String UNPROCESSABLE = "Couldn't process the request. Please try again later.";

	protected ResponseEntity<Message> toSucess(int identifier, String msg) {

		Message message = new Message.MessageBuilder()
				.identifier(identifier)
				.detailMessage(msg)
				.build();

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected ResponseEntity<Message> to500() {

		Message message = new Message.MessageBuilder()
				.error(INTERNAL_ERROR)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}

	protected ResponseEntity<Message> toFailedDependency() {

		Message message = new Message.MessageBuilder()
				.error(UNPROCESSABLE)
				.build();
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(message);
	}

	protected ResponseEntity<Message> to401(String msg) {
		Message message = new Message.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	protected ResponseEntity<Message> to400(String msg) {
		Message message = new Message.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	protected ResponseEntity<Message> to401() {
		Message message = new Message.MessageBuilder()
				.error(UNAUTHORIZED)
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	protected ResponseEntity<Message> to404(String msg) {
		Message message = new Message.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	protected ResponseEntity<Message> to404(Object msg) {
		Message message = new Message.MessageBuilder()
				.error(msg.toString())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	protected ResponseEntity<Object> toSucessCreate(Object data) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	protected ResponseEntity<Object> toSucessCreate(String data) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	protected ResponseEntity<Object> toError500(Object data) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
	}

	protected ResponseEntity<Object> toError400(Object data) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	}

	protected ResponseEntity<Object> toError404(Object data) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
	}

	protected ResponseEntity<Object> toError400(String data) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	}

	protected ResponseEntity<Object> toSuccess(Object object) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(object);
	}

	protected ResponseEntity<Message> toSucess(Object object) {
		Message message = new Message.MessageBuilder()
				.data(object)
				.build();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected ResponseEntity<Message> toSucess404(Object object) {
		Message message = new Message.MessageBuilder()
				.data(object)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(message);
	}
}