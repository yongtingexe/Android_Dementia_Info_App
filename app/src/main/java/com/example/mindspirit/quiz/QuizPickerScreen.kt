package com.example.mindspirit.quiz

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun QuizPickerScreen() {
    val quizList = remember { mutableStateListOf<Quiz>() }
    readQuiz(quizList)

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = quizList) { quiz ->
            ListItem(quiz = quiz)
        }
    }

}

@Composable
fun ListItem(quiz: Quiz){
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Column(modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
        ){
            Row{
                quiz.completed?.let {
                    Checkbox(checked = it, onCheckedChange = { quiz.number?.let { it1 ->
                        updateProgress(it, it1)
                    } })
                }

                Column(modifier = Modifier.weight(1f)){
                    Text(text = "Quiz")
                    Text(text = quiz.number.toString(), style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))
                }
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Start") }
            }
        }
    }
}

private fun readQuiz(quizList: SnapshotStateList<Quiz>){
    val db = FirebaseFirestore.getInstance()
    db.collection("Quiz").get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                quizList.add(document.toObject(Quiz::class.java))
            }

        }
        .addOnFailureListener {
            Log.i(ContentValues.TAG, "Quiz Not Found")
        }
}

private fun updateProgress(selected: Boolean, number: Long) = CoroutineScope(Dispatchers.IO).launch{
    val map = mutableMapOf<String, Any>()
    map["completed"] = selected
    map["number"] = number

    val db = Firebase.firestore.collection("Quiz")
    val query = db.whereEqualTo("number", number).get().await()

    if(query.documents.isNotEmpty()){
        for(document in query){
            Log.d("TAG", "Document: $document")
            db.document(document.id).update(map)
        }
    }else
        Log.i("TAG", "No match found")
}
