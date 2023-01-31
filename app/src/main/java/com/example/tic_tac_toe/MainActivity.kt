package com.example.tic_tac_toe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tic_tac_toe.ui.theme.TictactoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TictactoeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    tictactoe()
                }
            }
        }
    }
}


@Composable
fun tictactoe() {
    //Text(text = "Tic-Tac-Toe")


    var buttonClickedStartState = remember {
        mutableStateOf(false)
    }

    var buttonClickedResetState = remember{
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            color = Color.Black,
            shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {

            CreateHeader()

        }

        Row() {
            //**START GAME
            Button(modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 90.dp),


                onClick = {
                    buttonClickedStartState.value=true;
                }
            )
            {
                Text(
                    text = "Start Game!",
                    style = MaterialTheme.typography.button
                )
            }
            //**RESET GAME
            Button(modifier = Modifier.padding(5.dp),


                onClick = {
                    buttonClickedResetState.value=true;

                }
            )
            {
                Text(
                    text = "Reset Game",
                    style = MaterialTheme.typography.button
                )
            }

            //Start of boxes



        }
        if(buttonClickedStartState.value)
            createBox()

        if(buttonClickedResetState.value)
            Box(modifier = Modifier.background(Color.Magenta)) {

            }


        Row(modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,

        ) {

            Text(text = "Player 1: 0       ")

            Text(text = "Player 2: 1")
        }


    }


}

@Composable
private fun createBox() {
    Column() {

        Row() {
            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }
        }

        Row() {
            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }
        }

        Row() {
            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }

            Box(
                modifier = Modifier
                    .padding(7.dp)
                    .size(100.dp)
                    .background(Color.DarkGray)
            ) {

                Text(text = "*")
            }
        }

    }
}

@Composable
private fun CreateHeader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tic Tac Toe",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 50.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TictactoeTheme {
        tictactoe()
    }
}