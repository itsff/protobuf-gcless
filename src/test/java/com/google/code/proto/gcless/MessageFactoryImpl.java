package com.google.code.proto.gcless;

import protobuf_gcless_unittest.ForeignMessageImpl;
import protobuf_gcless_unittest.TestAllTypesImpl;

public class MessageFactoryImpl implements MessageFactory {

	public Object create(String fullMessageName) {
		if( fullMessageName.equals("protobuf_gcless_unittest.UnittestProto.TestAllTypes") ) {
			return new TestAllTypesImpl();
		}
		if( fullMessageName.equals("protobuf_gcless_unittest.UnittestProto.ForeignMessage") ) {
			return new ForeignMessageImpl();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
}





