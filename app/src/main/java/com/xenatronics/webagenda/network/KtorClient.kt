package com.xenatronics.webagenda.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*

import io.ktor.client.features.json.*

import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


object KtorClient {
    val json= Json {
        encodeDefaults=true
        ignoreUnknownKeys=true
    }

    val httpClient= HttpClient(){
        install(JsonFeature){
            serializer=KotlinxSerializer(json)
        }
        install(Logging){
            level=LogLevel.ALL
        }
        install(HttpTimeout){
            socketTimeoutMillis=60_000
            requestTimeoutMillis=60_000
            connectTimeoutMillis=60_000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}