/*
 * Fixture Monkey
 *
 * Copyright (c) 2021-present NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.fixturemonkey.api.option;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javax.annotation.Nullable;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

import com.navercorp.fixturemonkey.api.customizer.FixtureCustomizer;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfoGenerator;
import com.navercorp.fixturemonkey.api.generator.ArbitraryGenerator;
import com.navercorp.fixturemonkey.api.generator.ArbitraryPropertyGenerator;
import com.navercorp.fixturemonkey.api.generator.DefaultArbitraryGenerator;
import com.navercorp.fixturemonkey.api.generator.DefaultNullInjectGenerator;
import com.navercorp.fixturemonkey.api.generator.JavaDefaultArbitraryGeneratorBuilder;
import com.navercorp.fixturemonkey.api.generator.NullInjectGenerator;
import com.navercorp.fixturemonkey.api.introspector.ArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.JavaArbitraryResolver;
import com.navercorp.fixturemonkey.api.introspector.JavaTimeArbitraryResolver;
import com.navercorp.fixturemonkey.api.introspector.JavaTimeTypeArbitraryGenerator;
import com.navercorp.fixturemonkey.api.introspector.JavaTypeArbitraryGenerator;
import com.navercorp.fixturemonkey.api.matcher.Matcher;
import com.navercorp.fixturemonkey.api.matcher.MatcherOperator;
import com.navercorp.fixturemonkey.api.plugin.Plugin;
import com.navercorp.fixturemonkey.api.property.PropertyNameResolver;

@API(since = "0.4.0", status = Status.EXPERIMENTAL)
public final class GenerateOptionsBuilder {
	private List<MatcherOperator<ArbitraryPropertyGenerator>> arbitraryPropertyGenerators =
		new ArrayList<>(GenerateOptions.DEFAULT_ARBITRARY_PROPERTY_GENERATORS);
	private ArbitraryPropertyGenerator defaultArbitraryPropertyGenerator;
	private List<MatcherOperator<PropertyNameResolver>> propertyNameResolvers = new ArrayList<>();
	private PropertyNameResolver defaultPropertyNameResolver;
	private List<MatcherOperator<NullInjectGenerator>> nullInjectGenerators = new ArrayList<>();
	private NullInjectGenerator defaultNullInjectGenerator;
	private List<MatcherOperator<ArbitraryContainerInfoGenerator>> arbitraryContainerInfoGenerators = new ArrayList<>();
	private Integer defaultArbitraryContainerMaxSize;
	private ArbitraryContainerInfo defaultArbitraryContainerInfo;
	private List<MatcherOperator<ArbitraryGenerator>> arbitraryGenerators = new ArrayList<>();
	private ArbitraryGenerator defaultArbitraryGenerator;

	@SuppressWarnings("rawtypes")
	private List<MatcherOperator<FixtureCustomizer>> arbitraryCustomizers = new ArrayList<>();
	private final JavaDefaultArbitraryGeneratorBuilder javaDefaultArbitraryGeneratorBuilder =
		DefaultArbitraryGenerator.javaBuilder();

	GenerateOptionsBuilder() {
	}

	public GenerateOptionsBuilder arbitraryPropertyGenerators(
		List<MatcherOperator<ArbitraryPropertyGenerator>> arbitraryPropertyGenerators
	) {
		this.arbitraryPropertyGenerators = arbitraryPropertyGenerators;
		return this;
	}

	public GenerateOptionsBuilder insertFirstArbitraryPropertyGenerator(
		MatcherOperator<ArbitraryPropertyGenerator> arbitraryPropertyGenerator
	) {
		List<MatcherOperator<ArbitraryPropertyGenerator>> result =
			insertFirst(this.arbitraryPropertyGenerators, arbitraryPropertyGenerator);
		return this.arbitraryPropertyGenerators(result);
	}

	public GenerateOptionsBuilder insertFirstArbitraryPropertyGenerator(
		Matcher matcher,
		ArbitraryPropertyGenerator arbitraryPropertyGenerator
	) {
		return this.insertFirstArbitraryPropertyGenerator(new MatcherOperator<>(matcher, arbitraryPropertyGenerator));
	}

	public GenerateOptionsBuilder insertFirstArbitraryPropertyGenerator(
		Class<?> type,
		ArbitraryPropertyGenerator arbitraryPropertyGenerator
	) {
		return this.insertFirstArbitraryPropertyGenerator(
			MatcherOperator.assignableTypeMatchOperator(type, arbitraryPropertyGenerator)
		);
	}

	public GenerateOptionsBuilder defaultArbitraryPropertyGenerator(
		ArbitraryPropertyGenerator defaultArbitraryPropertyGenerator
	) {
		this.defaultArbitraryPropertyGenerator = defaultArbitraryPropertyGenerator;
		return this;
	}

	public GenerateOptionsBuilder propertyNameResolvers(
		List<MatcherOperator<PropertyNameResolver>> propertyNameResolvers
	) {
		this.propertyNameResolvers = propertyNameResolvers;
		return this;
	}

	public GenerateOptionsBuilder insertFirstPropertyNameResolver(
		MatcherOperator<PropertyNameResolver> propertyNameResolver
	) {
		List<MatcherOperator<PropertyNameResolver>> result =
			insertFirst(this.propertyNameResolvers, propertyNameResolver);
		return this.propertyNameResolvers(result);
	}

	public GenerateOptionsBuilder insertFirstPropertyNameResolver(
		Matcher matcher,
		PropertyNameResolver propertyNameResolver
	) {
		return this.insertFirstPropertyNameResolver(new MatcherOperator<>(matcher, propertyNameResolver));
	}

	public GenerateOptionsBuilder insertFirstPropertyNameResolver(
		Class<?> type,
		PropertyNameResolver propertyNameResolver
	) {
		return this.insertFirstPropertyNameResolver(
			MatcherOperator.assignableTypeMatchOperator(type, propertyNameResolver)
		);
	}

	public GenerateOptionsBuilder defaultPropertyNameResolver(PropertyNameResolver defaultPropertyNameResolver) {
		this.defaultPropertyNameResolver = defaultPropertyNameResolver;
		return this;
	}

	public GenerateOptionsBuilder nullInjectGenerators(
		List<MatcherOperator<NullInjectGenerator>> nullInjectGenerators
	) {
		this.nullInjectGenerators = nullInjectGenerators;
		return this;
	}

	public GenerateOptionsBuilder insertFirstNullInjectGenerators(
		MatcherOperator<NullInjectGenerator> nullInjectGenerator
	) {
		List<MatcherOperator<NullInjectGenerator>> result =
			insertFirst(this.nullInjectGenerators, nullInjectGenerator);
		return this.nullInjectGenerators(result);
	}

	public GenerateOptionsBuilder insertFirstNullInjectGenerators(
		Matcher matcher,
		NullInjectGenerator nullInjectGenerator
	) {
		return this.insertFirstNullInjectGenerators(new MatcherOperator<>(matcher, nullInjectGenerator));
	}

	public GenerateOptionsBuilder insertFirstNullInjectGenerators(
		Class<?> type,
		NullInjectGenerator nullInjectGenerator
	) {
		return this.insertFirstNullInjectGenerators(
			MatcherOperator.assignableTypeMatchOperator(type, nullInjectGenerator)
		);
	}

	public GenerateOptionsBuilder defaultNullInjectGenerator(NullInjectGenerator defaultNullInjectGenerator) {
		this.defaultNullInjectGenerator = defaultNullInjectGenerator;
		return this;
	}

	public GenerateOptionsBuilder arbitraryContainerInfoGenerators(
		List<MatcherOperator<ArbitraryContainerInfoGenerator>> arbitraryContainerInfoGenerators
	) {
		this.arbitraryContainerInfoGenerators = arbitraryContainerInfoGenerators;
		return this;
	}

	public GenerateOptionsBuilder insertFirstArbitraryContainerInfoGenerator(
		MatcherOperator<ArbitraryContainerInfoGenerator> arbitraryContainerInfoGenerator
	) {
		List<MatcherOperator<ArbitraryContainerInfoGenerator>> result =
			insertFirst(this.arbitraryContainerInfoGenerators, arbitraryContainerInfoGenerator);
		return this.arbitraryContainerInfoGenerators(result);
	}

	public GenerateOptionsBuilder insertFirstArbitraryContainerInfoGenerator(
		Matcher matcher,
		ArbitraryContainerInfoGenerator arbitraryContainerInfoGenerator
	) {
		return this.insertFirstArbitraryContainerInfoGenerator(
			new MatcherOperator<>(matcher, arbitraryContainerInfoGenerator)
		);
	}

	public GenerateOptionsBuilder insertFirstArbitraryContainerInfoGenerator(
		Class<?> type,
		ArbitraryContainerInfoGenerator arbitraryContainerInfoGenerator
	) {
		return this.insertFirstArbitraryContainerInfoGenerator(
			MatcherOperator.assignableTypeMatchOperator(type, arbitraryContainerInfoGenerator)
		);
	}

	public GenerateOptionsBuilder defaultArbitraryContainerMaxSize(int defaultArbitraryContainerMaxSize) {
		this.defaultArbitraryContainerMaxSize = defaultArbitraryContainerMaxSize;
		return this;
	}

	public GenerateOptionsBuilder defaultArbitraryContainerInfo(ArbitraryContainerInfo defaultArbitraryContainerInfo) {
		this.defaultArbitraryContainerInfo = defaultArbitraryContainerInfo;
		return this;
	}

	public GenerateOptionsBuilder arbitraryGenerators(
		List<MatcherOperator<ArbitraryGenerator>> arbitraryGenerators
	) {
		this.arbitraryGenerators = arbitraryGenerators;
		return this;
	}

	public GenerateOptionsBuilder insertFirstArbitraryGenerator(
		MatcherOperator<ArbitraryGenerator> arbitraryGenerator
	) {
		List<MatcherOperator<ArbitraryGenerator>> result =
			insertFirst(this.arbitraryGenerators, arbitraryGenerator);
		return this.arbitraryGenerators(result);
	}

	public GenerateOptionsBuilder insertFirstArbitraryGenerator(
		Matcher matcher,
		ArbitraryGenerator arbitraryGenerator
	) {
		return this.insertFirstArbitraryGenerator(
			new MatcherOperator<>(matcher, arbitraryGenerator)
		);
	}

	public GenerateOptionsBuilder insertFirstArbitraryGenerator(
		Class<?> type,
		ArbitraryGenerator arbitraryGenerator
	) {
		return this.insertFirstArbitraryGenerator(
			MatcherOperator.assignableTypeMatchOperator(type, arbitraryGenerator)
		);
	}

	public GenerateOptionsBuilder insertFirstArbitraryIntrospector(
		MatcherOperator<ArbitraryIntrospector> arbitraryIntrospector
	) {
		return this.insertFirstArbitraryIntrospector(
			arbitraryIntrospector.getMatcher(),
			arbitraryIntrospector.getOperator()
		);
	}

	public GenerateOptionsBuilder insertFirstArbitraryIntrospector(
		Matcher matcher,
		ArbitraryIntrospector arbitraryIntrospector
	) {
		return this.insertFirstArbitraryGenerator(
			matcher,
			new DefaultArbitraryGenerator(arbitraryIntrospector)
		);
	}

	public GenerateOptionsBuilder insertFirstArbitraryIntrospector(
		Class<?> type,
		ArbitraryIntrospector arbitraryIntrospector
	) {
		return this.insertFirstArbitraryGenerator(
			MatcherOperator.assignableTypeMatchOperator(type, new DefaultArbitraryGenerator(arbitraryIntrospector))
		);
	}

	public GenerateOptionsBuilder defaultArbitraryGenerator(ArbitraryGenerator defaultArbitraryGenerator) {
		this.defaultArbitraryGenerator = defaultArbitraryGenerator;
		return this;
	}

	public GenerateOptionsBuilder priorityIntrospector(
		UnaryOperator<ArbitraryIntrospector> priorityIntrospector
	) {
		this.javaDefaultArbitraryGeneratorBuilder.priorityIntrospector(priorityIntrospector);
		return this;
	}

	public GenerateOptionsBuilder containerIntrospector(
		UnaryOperator<ArbitraryIntrospector> containerIntrospector
	) {
		this.javaDefaultArbitraryGeneratorBuilder.containerIntrospector(containerIntrospector);
		return this;
	}

	public GenerateOptionsBuilder objectIntrospector(
		UnaryOperator<ArbitraryIntrospector> objectIntrospector
	) {
		this.javaDefaultArbitraryGeneratorBuilder.objectIntrospector(objectIntrospector);
		return this;
	}

	public GenerateOptionsBuilder javaTypeArbitraryGenerator(
		JavaTypeArbitraryGenerator javaTypeArbitraryGenerator
	) {
		this.javaDefaultArbitraryGeneratorBuilder.javaTypeArbitraryGenerator(javaTypeArbitraryGenerator);
		return this;
	}

	public GenerateOptionsBuilder javaArbitraryResolver(
		JavaArbitraryResolver javaArbitraryResolver
	) {
		this.javaDefaultArbitraryGeneratorBuilder.javaArbitraryResolver(javaArbitraryResolver);
		return this;
	}

	public GenerateOptionsBuilder javaTimeTypeArbitraryGenerator(
		JavaTimeTypeArbitraryGenerator javaTimeTypeArbitraryGenerator
	) {
		this.javaDefaultArbitraryGeneratorBuilder.javaTimeTypeArbitraryGenerator(javaTimeTypeArbitraryGenerator);
		return this;
	}

	public GenerateOptionsBuilder javaTimeArbitraryResolver(
		JavaTimeArbitraryResolver javaTimeArbitraryResolver
	) {
		this.javaDefaultArbitraryGeneratorBuilder.javaTimeArbitraryResolver(javaTimeArbitraryResolver);
		return this;
	}

	public GenerateOptionsBuilder plugin(Plugin plugin) {
		plugin.accept(this);
		return this;
	}

	@SuppressWarnings("rawtypes")
	public GenerateOptionsBuilder arbitraryCustomizers(
		List<MatcherOperator<FixtureCustomizer>> arbitraryCustomizers
	) {
		this.arbitraryCustomizers = arbitraryCustomizers;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public GenerateOptionsBuilder insertFirstArbitraryCustomizer(
		MatcherOperator<FixtureCustomizer> arbitraryCustomizer
	) {
		List<MatcherOperator<FixtureCustomizer>> result =
			insertFirst(this.arbitraryCustomizers, arbitraryCustomizer);
		return arbitraryCustomizers(result);
	}

	public GenerateOptionsBuilder insertFirstArbitraryCustomizer(
		Matcher matcher,
		FixtureCustomizer<?> fixtureCustomizer
	) {
		return this.insertFirstArbitraryCustomizer(
			new MatcherOperator<>(matcher, fixtureCustomizer)
		);
	}

	public <T> GenerateOptionsBuilder insertFirstArbitraryCustomizer(
		Class<T> type,
		FixtureCustomizer<? extends T> fixtureCustomizer
	) {
		return this.insertFirstArbitraryCustomizer(
			MatcherOperator.assignableTypeMatchOperator(type, fixtureCustomizer)
		);
	}

	public GenerateOptions build() {
		ArbitraryPropertyGenerator defaultArbitraryPropertyGenerator = defaultIfNull(
			this.defaultArbitraryPropertyGenerator,
			() -> GenerateOptions.DEFAULT_ARBITRARY_PROPERTY_GENERATOR
		);
		PropertyNameResolver defaultPropertyNameResolver = defaultIfNull(
			this.defaultPropertyNameResolver,
			() -> GenerateOptions.DEFAULT_PROPERTY_NAME_RESOLVER
		);
		NullInjectGenerator defaultNullInjectGenerator =
			defaultIfNull(this.defaultNullInjectGenerator, DefaultNullInjectGenerator::new);
		int defaultArbitraryContainerMaxSize = defaultIfNull(
			this.defaultArbitraryContainerMaxSize,
			() -> GenerateOptions.DEFAULT_ARBITRARY_CONTAINER_MAX_SIZE
		);
		ArbitraryContainerInfo defaultArbitraryContainerInfo =
			defaultIfNull(
				this.defaultArbitraryContainerInfo,
				() -> new ArbitraryContainerInfo(0, defaultArbitraryContainerMaxSize)
			);
		ArbitraryGenerator defaultArbitraryGenerator =
			defaultIfNull(this.defaultArbitraryGenerator, this.javaDefaultArbitraryGeneratorBuilder::build);

		return new GenerateOptions(
			this.arbitraryPropertyGenerators,
			defaultArbitraryPropertyGenerator,
			this.propertyNameResolvers,
			defaultPropertyNameResolver,
			this.nullInjectGenerators,
			defaultNullInjectGenerator,
			this.arbitraryContainerInfoGenerators,
			defaultArbitraryContainerMaxSize,
			defaultArbitraryContainerInfo,
			this.arbitraryGenerators,
			defaultArbitraryGenerator,
			this.arbitraryCustomizers
		);
	}

	private static <T> T defaultIfNull(@Nullable T obj, Supplier<T> defaultValue) {
		return obj != null ? obj : defaultValue.get();
	}

	private static <T> List<T> insertFirst(List<T> list, T value) {
		List<T> result = new ArrayList<>();
		result.add(value);
		result.addAll(list);
		return result;
	}
}
