package com.example.PR23106SborschikovPR23

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class AnalyzeItem(val name: String)
data class NewsItem(val news: String)

class Analyzes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = listOf(
            AnalyzeItem("Анализ 1"),
            AnalyzeItem("Анализ 2"),
            AnalyzeItem("Анализ 3")
        )
        val news = listOf(
            NewsItem("Новость 1"),
            NewsItem("Новость 2"),
            NewsItem("Новость 3")
        )

        setContent {
            AnalyzesScreen(data, news)
        }
    }
}

@Composable
fun AnalyzesScreen(yourDataList: List<AnalyzeItem>, newsList: List<NewsItem>) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    LazyColumn {
        item {
            CatalogTabs(selectedTabIndex) { index ->
                selectedTabIndex = index
            }
        }
        item {
            SearchBar { query ->
                // Обработка поиска
                println("Поиск: $query")
            }
        }
        items(yourDataList) { item ->
            Text(text = item.name)
        }
        item {
            Text(text = "Новости", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        }
        items(newsList) { news ->
            NewsAndStocksSection(newsList)
        }
    }
}

@Composable
fun NewsAndStocksSection(newsList: List<NewsItem>) {
    LazyRow {
        items(newsList) { news ->
            Card {
                Text(text = news.news)
            }
        }
    }
}

@Composable
fun CatalogTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        val tabs = listOf("Популярные", "Covid", "Комплексные")
        tabs.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        label = { Text("Поиск анализов") }
    )
}