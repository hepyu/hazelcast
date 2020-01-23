/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Adds a new set configuration to a running cluster.
 * If a set configuration with the given {@code name} already exists, then
 * the new configuration is ignored and the existing one is preserved.
 */
@Generated("c2a628aec93f4f10ec1124674d755992")
public final class DynamicConfigAddSetConfigCodec {
    //hex: 0x1B0500
    public static final int REQUEST_MESSAGE_TYPE = 1770752;
    //hex: 0x1B0501
    public static final int RESPONSE_MESSAGE_TYPE = 1770753;
    private static final int REQUEST_BACKUP_COUNT_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_ASYNC_BACKUP_COUNT_FIELD_OFFSET = REQUEST_BACKUP_COUNT_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_MAX_SIZE_FIELD_OFFSET = REQUEST_ASYNC_BACKUP_COUNT_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_STATISTICS_ENABLED_FIELD_OFFSET = REQUEST_MAX_SIZE_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_MERGE_BATCH_SIZE_FIELD_OFFSET = REQUEST_STATISTICS_ENABLED_FIELD_OFFSET + BOOLEAN_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_FRAME_SIZE = REQUEST_MERGE_BATCH_SIZE_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;

    private DynamicConfigAddSetConfigCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * set's name
         */
        public java.lang.String name;

        /**
         * item listener configurations
         */
        public @Nullable java.util.List<com.hazelcast.client.impl.protocol.task.dynamicconfig.ListenerConfigHolder> listenerConfigs;

        /**
         * number of synchronous backups
         */
        public int backupCount;

        /**
         * number of asynchronous backups
         */
        public int asyncBackupCount;

        /**
         * maximum size of the set
         */
        public int maxSize;

        /**
         * {@code true} to enable gathering of statistics on the list, otherwise {@code false}
         */
        public boolean statisticsEnabled;

        /**
         * name of an existing configured split brain protection to be used to determine the minimum number of members
         * required in the cluster for the lock to remain functional. When {@code null}, split brain protection does not
         * apply to this lock configuration's operations.
         */
        public @Nullable java.lang.String splitBrainProtectionName;

        /**
         * Name of a class implementing SplitBrainMergePolicy that handles merging of values for this cache
         * while recovering from network partitioning.
         */
        public java.lang.String mergePolicy;

        /**
         * Number of entries to be sent in a merge operation.
         */
        public int mergeBatchSize;
    }

    public static ClientMessage encodeRequest(java.lang.String name, @Nullable java.util.Collection<com.hazelcast.client.impl.protocol.task.dynamicconfig.ListenerConfigHolder> listenerConfigs, int backupCount, int asyncBackupCount, int maxSize, boolean statisticsEnabled, @Nullable java.lang.String splitBrainProtectionName, java.lang.String mergePolicy, int mergeBatchSize) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(false);
        clientMessage.setOperationName("DynamicConfig.AddSetConfig");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        encodeInt(initialFrame.content, REQUEST_BACKUP_COUNT_FIELD_OFFSET, backupCount);
        encodeInt(initialFrame.content, REQUEST_ASYNC_BACKUP_COUNT_FIELD_OFFSET, asyncBackupCount);
        encodeInt(initialFrame.content, REQUEST_MAX_SIZE_FIELD_OFFSET, maxSize);
        encodeBoolean(initialFrame.content, REQUEST_STATISTICS_ENABLED_FIELD_OFFSET, statisticsEnabled);
        encodeInt(initialFrame.content, REQUEST_MERGE_BATCH_SIZE_FIELD_OFFSET, mergeBatchSize);
        clientMessage.add(initialFrame);
        StringCodec.encode(clientMessage, name);
        ListMultiFrameCodec.encodeNullable(clientMessage, listenerConfigs, ListenerConfigHolderCodec::encode);
        CodecUtil.encodeNullable(clientMessage, splitBrainProtectionName, StringCodec::encode);
        StringCodec.encode(clientMessage, mergePolicy);
        return clientMessage;
    }

    public static DynamicConfigAddSetConfigCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        request.backupCount = decodeInt(initialFrame.content, REQUEST_BACKUP_COUNT_FIELD_OFFSET);
        request.asyncBackupCount = decodeInt(initialFrame.content, REQUEST_ASYNC_BACKUP_COUNT_FIELD_OFFSET);
        request.maxSize = decodeInt(initialFrame.content, REQUEST_MAX_SIZE_FIELD_OFFSET);
        request.statisticsEnabled = decodeBoolean(initialFrame.content, REQUEST_STATISTICS_ENABLED_FIELD_OFFSET);
        request.mergeBatchSize = decodeInt(initialFrame.content, REQUEST_MERGE_BATCH_SIZE_FIELD_OFFSET);
        request.name = StringCodec.decode(iterator);
        request.listenerConfigs = ListMultiFrameCodec.decodeNullable(iterator, ListenerConfigHolderCodec::decode);
        request.splitBrainProtectionName = CodecUtil.decodeNullable(iterator, StringCodec::decode);
        request.mergePolicy = StringCodec.decode(iterator);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {
    }

    public static ClientMessage encodeResponse() {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        clientMessage.add(initialFrame);

        return clientMessage;
    }

    public static DynamicConfigAddSetConfigCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        //empty initial frame
        iterator.next();
        return response;
    }

}
