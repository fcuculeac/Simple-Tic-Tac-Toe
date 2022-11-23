package tictactoe

fun main() {

    val gameMatrix: MutableList<MutableList<String>> =
        mutableListOf(mutableListOf(" ", " ", " "),
            mutableListOf(" ", " ", " "),
            mutableListOf(" ", " ", " "))
    var gameIsOver = false

    printData(gameMatrix)

    while (true) {
        inputUserData(gameMatrix, "X")
        printData(gameMatrix)
        if (checkGameState(gameMatrix) != "Game not finished") {
            println(checkGameState(gameMatrix))
            break
        }
        inputUserData(gameMatrix, "O")
        printData(gameMatrix)
        if (checkGameState(gameMatrix) != "Game not finished") {
            println(checkGameState(gameMatrix))
            break
        }
    }

    /*
    val inputText = readln()
    if (inputText.length != 9) println("bad input!")

    gameMatrix[0][0] = inputText[0].toString()
    gameMatrix[0][1] = inputText[1].toString()
    gameMatrix[0][2] = inputText[2].toString()

    gameMatrix[1][0] = inputText[3].toString()
    gameMatrix[1][1] = inputText[4].toString()
    gameMatrix[1][2] = inputText[5].toString()

    gameMatrix[2][0] = inputText[6].toString()
    gameMatrix[2][1] = inputText[7].toString()
    gameMatrix[2][2] = inputText[8].toString()


    printData(gameMatrix)
    inputUserData(gameMatrix)
    printData(gameMatrix)
//    println(checkGameState(gameMatrix))
    */
}

fun inputUserData(game: MutableList<MutableList<String>>, xOrO: String) {
    var isValidInput = true
    var x: String
    var y: String
    do {
        print("> ")
        val inputText = readln()
        if (inputText.contains(' ')) {
            x = inputText.split(" ")[0]
            y = inputText.split(" ")[1]
        } else {
            x = inputText
            y = ""
        }

        isValidInput = true

        if (x.toIntOrNull() == null || y.toIntOrNull() == null) {
            println("You should enter numbers!")
            isValidInput = false
        } else if (x.toIntOrNull() !in 1..3 || y.toIntOrNull() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            isValidInput = false
        } else if (game[x.toInt() - 1][y.toInt() - 1] != " " ) {
            println("This cell is occupied! Choose another one!")
            isValidInput = false
        }
    } while (!isValidInput)

    game[x.toInt() - 1][y.toInt() - 1] = xOrO

}


fun checkGameState(game: MutableList<MutableList<String>>): String {
    var xIsWinner = false
    var oIsWinner = false
    var countOfX = 0
    var countOfO = 0
    var countOfEmpty = 0

    for (row in game) {
        for (col in row) {
            if (col == "X") countOfX++
            if (col == "O") countOfO++
            if (col == " ") countOfEmpty++
        }
    }

//    check rows, columns, diagonals for x
    if (game[0].joinToString("") == "XXX" ||
        game[1].joinToString("") == "XXX" ||
        game[2].joinToString("") == "XXX" ||

        game[0][0] + game[1][0] + game[2][0] == "XXX" ||
        game[0][1] + game[1][1] + game[2][1] == "XXX" ||
        game[0][2] + game[1][2] + game[2][2] == "XXX" ||

        game[0][0] + game[1][1] + game[2][2] == "XXX" ||
        game[0][2] + game[1][1] + game[2][0] == "XXX"
    )
        xIsWinner = true

    if (game[0].joinToString("") == "OOO" ||
        game[1].joinToString("") == "OOO" ||
        game[2].joinToString("") == "OOO" ||

        game[0][0] + game[1][0] + game[2][0] == "OOO" ||
        game[0][1] + game[1][1] + game[2][1] == "OOO" ||
        game[0][2] + game[1][2] + game[2][2] == "OOO" ||

        game[0][0] + game[1][1] + game[2][2] == "OOO" ||
        game[0][2] + game[1][1] + game[2][0] == "OOO"
    )
        oIsWinner = true

    if (xIsWinner && oIsWinner ||
        countOfX - countOfO > 1 ||
        countOfO - countOfX > 1) return "Impossible"
    if (xIsWinner) return "X wins"
    if (oIsWinner) return "O wins"
    if (countOfEmpty > 0) return "Game not finished"

    return "Draw"
}

fun printData(game: MutableList<MutableList<String>>) {
    val separator = "---------"
    println(separator)
    println("| ${game[0].joinToString(" ")} |")
    println("| ${game[1].joinToString(" ")} |")
    println("| ${game[2].joinToString(" ")} |")
    println(separator)
}