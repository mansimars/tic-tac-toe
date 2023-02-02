package com.example.tic_tac_toe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tic_tac_toe.logic.Game
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
                    Tictactoe()
                }
            }
        }
    }
}

var g1= Game()
@Composable
fun Tictactoe() {
    //Text(text = "Tic-Tac-Toe")


    var startState = remember { mutableStateOf(false) }
    var resetState = remember { mutableStateOf( false )}
    var scoreState = remember{ mutableStateOf(false)}


    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .fillMaxHeight(),
            //.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            color = Color.Black,
            //shape = RoundedCornerShape(corner = CornerSize(15.dp))
        ) {

            CreateHeader()

        }

        Row() {
            //**START GAME
            Button(modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 20.dp),


                onClick = {
                    startState.value=true;
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
                    resetState.value=true;
                    startState.value=false;

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
        if(startState.value )
        {
            createBox()

            Button(modifier = Modifier
                .padding(5.dp)
                .padding(top = 25.dp, bottom = 20.dp),


                onClick = {
                    g1.setaPlayer1Score()
                    g1.setaPlayer2Score()
                    resetState.value=true;
                    startState.value=false;
                }
            ){
                Text(
                    text = "Reset Score",
                    style = MaterialTheme.typography.button
                )
            }
        }
        else
        {
            Box() {

            }
        }



        if(resetState.value)
        {
            g1.resetGame()
            startState.value=true
        }

        Row(modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,

        ) {

        Column(){
            if(scoreState.value || !scoreState.value) {
                Text(
                    fontSize=15.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    //text = "Player ${g1.getaPlayer1Sign()}: ${g1.getaPlayer1Score()}       "
                    text = "Player ${g1.getaPlayer1Sign()}       Player ${g1.getaPlayer2Sign()}"
                )

                Text(
                    fontSize=15.sp,
                    color = Color.White,
                    text = "      ${g1.getaPlayer1Score()}                   ${g1.getaPlayer2Score()}"
                )
            }
        }
        }


    }


}

@Composable
fun alertDialog(state:Boolean, winOrFull: String){
    val openDialog = remember { mutableStateOf(state) }
    var messageToShow:String = ""

    if (winOrFull=="won")
        messageToShow=g1.getaWinner()
    else if(winOrFull=="full")
        messageToShow="Draw Match!"

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "RESULTS")
            },
            text = {
                Text(
                    messageToShow
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick =
                        {
                            openDialog.value = false
                            g1.resetGame();
                        }
                    ) {
                        Text("View Game")

                    }

                }
            }
        )
    }

}

//@Preview(showBackground = true)
@Composable
private fun createBox() {

    var winOrFull:String = "none"

    var winState = remember{ mutableStateOf(false) }
    var fullState = remember{ mutableStateOf(false)}


    Box(modifier = Modifier
        .size(220.dp)
        .background(Color.Black)) {


        Column {

            for (i in 0..2) {
                if(i != 0)
                Divider(color= Color.White, thickness = 5.dp)

                Row(modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth())
                {
                    for (j in 0..2) {

                        var buttonClickState = remember{
                            mutableStateOf("")
                        }

                        if(j !=0)
                            Divider(modifier = Modifier
                                .fillMaxHeight()
                                .width(5.dp), color= Color.White, thickness = 5.dp)

                        Button(colors = ButtonDefaults.buttonColors(Color.Black),
                            modifier = Modifier
                                .size(70.dp),
                            onClick = {

                                //if block is non empty
                                if(g1.blockEmpty(i,j)) {
                                    buttonClickState.value = g1.getValue(i, j)
                                    winOrFull = g1.checkWonOrFull()

                                    if(winOrFull.equals("won"))
                                        winState.value = true
                                    else if (winOrFull.equals("full"))
                                        fullState.value=true


                                }
                            })

                        {
                            Text(text = buttonClickState.value,
                                fontSize = 30.sp,
                                color = Color.White)
                        }

                }
                }
            }

            if(winState.value) {
                alertDialog(state = true,"won")
            }
            if(fullState.value){
                alertDialog(state = true,"full")
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
            fontStyle = FontStyle.Italic,
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
        Tictactoe()
    }
}