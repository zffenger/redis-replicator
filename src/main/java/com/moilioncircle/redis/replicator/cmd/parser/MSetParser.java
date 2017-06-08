/*
 * Copyright 2016 leon chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.redis.replicator.cmd.parser;

import com.moilioncircle.redis.replicator.cmd.CommandParser;
import com.moilioncircle.redis.replicator.cmd.impl.MSetCommand;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.moilioncircle.redis.replicator.cmd.parser.CommandParsers.objToBytes;
import static com.moilioncircle.redis.replicator.cmd.parser.CommandParsers.objToString;

/**
 * @author Leon Chen
 * @since 2.1.0
 */
public class MSetParser implements CommandParser<MSetCommand> {
    @Override
    public MSetCommand parse(Object[] command) {
        if (command.length == 1) return new MSetCommand(null, null);
        int idx = 1;
        Map<String, String> kv = new LinkedHashMap<>();
        Map<byte[], byte[]> rawKv = new LinkedHashMap<>();
        while (idx < command.length) {
            String key = objToString(command[idx]);
            byte[] rawKey = objToBytes(command[idx]);
            idx++;
            String value = idx == command.length ? null : objToString(command[idx]);
            byte[] rawValue = idx == command.length ? null : objToBytes(command[idx]);
            idx++;
            kv.put(key, value);
            rawKv.put(rawKey, rawValue);
        }
        return new MSetCommand(kv, rawKv);
    }

}
