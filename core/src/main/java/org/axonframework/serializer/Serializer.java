/*
 * Copyright (c) 2010-2011. Axon Framework
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

package org.axonframework.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Interface describing a serialization mechanism. Implementations can serialize objects of given type <code>T</code>
 * to an output stream and read the object back in from an input stream.
 *
 * @author Allard Buijze
 * @author Frank Versnel
 * @since 1.2
 */
public interface Serializer {

    /**
     * Serialize the given <code>object</code> into a byte[].
     *
     * @param object                 The object to serialize
     * @param expectedRepresentation The expected data type representing the serialized object
     * @param <T>                    The expected data type representing the serialized object
     * @return the instance representing the serialized object.
     */
    <T> SerializedObject<T> serialize(Object object, Class<T> expectedRepresentation);

    /**
     * Deserializes the first object read from the given <code>bytes</code>. The <code>bytes</code> are not consumed
     * from the array or modified in any way. The resulting object instances are cast to the expected types.
     *
     * @param serializedObject the instance describing the type of object and the bytes providing the serialized data
     * @param <T>              The data type of the serialized object
     * @return the serialized object, cast to the expected type
     *
     * @throws ClassCastException if the first object in the stream is not an instance of &lt;T&gt;.
     */
    <T> List<Object> deserialize(SerializedObject<T> serializedObject);

    /**
     * Returns the classes for the given type identifier. The result of this method must guarantee that the deserialized
     * SerializedObject with the given <code>type</code> are instances of the returned Classes. The order
     * of the classes has to be the same as the order of the deserialized objects so that each entry in the returned list
     * points to the entry on the same index in the list of the deserialized objects.
     *
     * @param type The type identifier of the object
     * @return the Class representing the type of the serialized Object.
     */
    List<Class> classForType(SerializedType type);
}
