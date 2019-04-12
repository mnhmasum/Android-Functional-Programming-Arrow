package com.artifactslab.newspan.features.details

import arrow.core.Either
import arrow.core.Try
import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.flatMap
import arrow.data.map
import arrow.effects.IO
import arrow.effects.IOConnection
import arrow.syntax.function.pipe
import com.artifactslab.newspan.dto.ArticlesItem
import com.artifactslab.newspan.retrofit.ApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


fun getNews(): Reader<DetailsActivityContext, Unit> =
        ReaderApi.ask<DetailsActivityContext>()
                .flatMap { (view) ->
                    view.showLoading() pipe {
                        fetchAllNews().map { io ->
                            io.unsafeRunAsync { reposEither ->
                                parseInput1(reposEither, view) pipe {
                                    view.hideLoading()
                                }
                            }
                        }

                    }
                }

fun parseInput1(e: Either<Throwable, List<ArticlesItem>?>, view: DetailsActivityView) =
        e.fold({ view.showMessage("Error in parse ") },
                { r ->
                    r!!.forEach { article ->
                        println(article.title)
                        println(article.description)
                        println(article.content)
                    } pipe {
                        view.showList(r!!)
                    }
                })

fun fetchAllNews(): Reader<DetailsActivityContext, IO<List<ArticlesItem>?>> =
        ReaderApi.ask<DetailsActivityContext>()
                .map { deps ->
                    IO.async { kindConnection: IOConnection, e: (Either<Throwable, List<ArticlesItem>?>) -> Unit ->
                        GlobalScope.launch {
                            e(queryForRepositories(deps.apiClient).toEither())
                        }
                    }
                }

private fun queryForRepositories(apiClient: ApiClient): Try<List<ArticlesItem>?> =
        Try {
            apiClient
                    .getRepositories("in", "51020d256c68430ba9bd415505885b3e")
                    .execute()
                    .body()!!
                    .articles
        }



