/*
 * Copyright 2011-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core.mapping;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bson.*;
import org.bson.types.Binary;
import org.bson.types.Code;
import org.bson.types.CodeWScope;
import org.bson.types.CodeWithScope;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.bson.types.Symbol;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import com.mongodb.DBRef;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.GeometryCollection;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.MultiLineString;
import com.mongodb.client.model.geojson.MultiPoint;
import com.mongodb.client.model.geojson.MultiPolygon;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;

/**
 * Simple constant holder for a {@link SimpleTypeHolder} enriched with Mongo specific simple types.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public abstract class MongoSimpleTypes {

	public static final Set<Class<?>> AUTOGENERATED_ID_TYPES = Set.of(ObjectId.class, String.class, BigInteger.class);
	private static final Set<Class<?>> MONGO_SIMPLE_TYPES = Set.of(Binary.class, DBRef.class, Decimal128.class,
			org.bson.Document.class, Code.class, CodeWScope.class, CodeWithScope.class, ObjectId.class, Pattern.class,
			Symbol.class, UUID.class, Instant.class, BsonValue.class, BsonNumber.class, BsonType.class, BsonArray.class,
			BsonSymbol.class, BsonUndefined.class, BsonMinKey.class, BsonMaxKey.class, BsonNull.class, BsonBinary.class,
			BsonBoolean.class, BsonDateTime.class, BsonDbPointer.class, BsonDecimal128.class, BsonDocument.class,
			BsonDouble.class, BsonInt32.class, BsonInt64.class, BsonJavaScript.class, BsonJavaScriptWithScope.class,
			BsonObjectId.class, BsonRegularExpression.class, BsonString.class, BsonTimestamp.class, Geometry.class,
			GeometryCollection.class, LineString.class, MultiLineString.class, MultiPoint.class, MultiPolygon.class,
			Point.class, Polygon.class);

	public static final SimpleTypeHolder HOLDER = new SimpleTypeHolder(MONGO_SIMPLE_TYPES, true) {

		@Override
		public boolean isSimpleType(Class<?> type) {

			if (type.isEnum()) {
				return true;
			}

			if (type.getName().startsWith("java.time")) {
				return false;
			}

			return super.isSimpleType(type);
		}
	};

	private MongoSimpleTypes() {}
}
