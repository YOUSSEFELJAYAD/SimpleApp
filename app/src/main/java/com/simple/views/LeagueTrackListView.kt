package com.simple.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simple.di.ProviderComposable.GProvider
import kiwi.orbit.compose.ui.controls.Card

import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar

@Composable
fun  LeagueTrackListView (hostController: NavHostController){
    val gProvider: GProvider = hiltViewModel()
    val list by remember { mutableStateOf(listOf(
        "League 1",
        "League 2",
        "League 3",
        "League 4",
        "League 5",
        "League 6",
        "League 7",
        "League 8",
        "League 9",
        "League 10",
        "League 11",
        "League 12",
        "League 13",
        "League 14",
        "League 15",
        "League 16",
        "League 17",
        "League 18",
        "League 19",
        "League 20",
        "League 21",
        "League 22",
        "League 23",
        "League 24",
        "League 25",
        "League 26",
        "League 27",
        "League 28",
        "League 29",
        "League 30",
        "League 31",
        "League 32",
        "League 33",
        "League 34",
        "League 35",
        "League 36",
        "League 37",
        "League 38",
        "League 39",
        "League 40",
        "League 41",
        "League 42",
        "League 43",
        "League 44",
        "League 45",
        "League 46",
        "League 47",
        "League 48",
        "League 49",
        "League 50",
        "League 51",
        "League 52",
        "League 53",
        "League 54",
        "League 55",
        "League 56",
        "League 57",
        "League 58",
        "League 59",
        "League 60",
        "League 61",
        "League 62",
        "League 63",
        "League 64",
        "League 65",
        "League 66",
        "League 67",
        "League 68",
        "League 69",
        "League 70",
        "League 71",
        "League 72",
        "League 73",
        "League 74",
        "League 75",
        "League 76",
        "League 77",
        "League 78",
        "League 79",
        "League 80",
        "League 81",
        "League 82",
        "League 83",
    )) }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "League Tracker List"
                    )
                },
                onNavigateUp = hostController::popBackStack
            )
        },
    ){
        Column (
            modifier = Modifier
                .padding(paddingValues = it)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true, // to reverse the list true or false (default is false)
            ) {
                items(list){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                      Card (
                          modifier = Modifier.padding(16.dp).fillMaxWidth()
                          ){
                        Text(
                            text = it,
                            modifier = Modifier.padding(16.dp)
                        )
                      }
                    }
                }
            }
        }
    }
}



