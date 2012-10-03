package com.google.code.proto.gcless;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.x.protobuf.Bug7;
import com.x.protobuf.Bug7Google;

public class Issue7SerializationTest {

	@Test
	public void testSerializeDeserialize() throws Exception {
		Bug7.SearchResponse message = getMessage();
		
		byte[] data = Bug7.SearchResponseSerializer.serialize(message);
		Bug7.SearchResponse result = Bug7.SearchResponseSerializer.parseFrom(data);
		assertNotNull(result);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Bug7.SearchResponseSerializer.serialize(message, baos);
		result = Bug7.SearchResponseSerializer.parseFrom(new ByteArrayInputStream(baos.toByteArray()));
		assertNotNull(result);
	}
	
	@Test
	public void testSerializeDeserializeWithOriginal() throws Exception {
		
		Bug7.SearchResponse message = getMessage();
		
		byte[] data = Bug7.SearchResponseSerializer.serialize(message);
		Bug7Google.SearchResponse response = Bug7Google.SearchResponse.parseFrom(data);
		assertMessagesEqual(message, response);
		data = response.toByteArray();
		message = Bug7.SearchResponseSerializer.parseFrom(data);
		assertMessagesEqual(message, response);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Bug7.SearchResponseSerializer.serialize(message, baos);
		response = Bug7Google.SearchResponse.parseFrom(new ByteArrayInputStream(baos.toByteArray()));
		assertMessagesEqual(message, response);
		baos = new ByteArrayOutputStream();
		response.writeTo(baos);
		message = Bug7.SearchResponseSerializer.parseFrom(new ByteArrayInputStream(baos.toByteArray()));
		assertMessagesEqual(message, response);
	}

	private Bug7.SearchResponse getMessage() {
		Bug7.SearchResponse.Result r1 = new Bug7.SearchResponse.Result();
		r1.setTitle("title");
		r1.setUrl("url");

		Bug7.SearchResponse.Result r2 = new Bug7.SearchResponse.Result();
		r2.setTitle("title2");
		r2.setUrl("url2");

		List<Bug7.SearchResponse.Result> results = new ArrayList<Bug7.SearchResponse.Result>();
		results.add(r1);
		results.add(r2);
		
		Bug7.SearchResponse.Test test = new Bug7.SearchResponse.Test();
		test.setTest("123");
		Bug7.SearchResponse.Test2 test2 = new Bug7.SearchResponse.Test2();
		test2.setTest(567);
		
		Bug7.SearchResponse message = new Bug7.SearchResponse();
		message.setResult(results);
		message.setTest(test);
		message.setTest2(test2);
		return message;
	}
	
	private static void assertMessagesEqual(Bug7.SearchResponse gcless, Bug7Google.SearchResponse orig) {
		assertEquals(gcless.getResult().size(), orig.getResultCount());
		for( int i=0;i<gcless.getResult().size();i++ ) {
			assertMessagesEqual(gcless.getResult().get(i), orig.getResult(i));
		}
		assertMessagesEqual(gcless.getTest(), orig.getTest());
		assertMessagesEqual(gcless.getTest2(), orig.getTest2());
	}
	
	private static void assertMessagesEqual(Bug7.SearchResponse.Result gcless, Bug7Google.SearchResponse.Result orig) {
		assertEquals(gcless.getTitle(), orig.getTitle());
		assertEquals(gcless.getUrl(), orig.getUrl());
	}
	private static void assertMessagesEqual(Bug7.SearchResponse.Test gcless, Bug7Google.SearchResponse.Test orig) {
		assertEquals(gcless.getTest(), orig.getTest());
	}
	private static void assertMessagesEqual(Bug7.SearchResponse.Test2 gcless, Bug7Google.SearchResponse.Test2 orig) {
		assertEquals(gcless.getTest(), orig.getTest());
	}
}
