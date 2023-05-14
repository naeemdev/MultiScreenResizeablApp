package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.domain.model.Note
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.theme.MultiScreenResizeablAppTheme

@Composable
fun NoteDetailComposable(
  modifier: Modifier = Modifier,
  note: Note = Note(subject = "ABC", text = "asdfasdfasdf")
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = note.subject,
      fontFamily = FontFamily.SansSerif,
      fontSize = 20.sp,
      fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.padding(vertical = 2.dp))

    Text(
      text = note.text,
      fontFamily = FontFamily.Serif,
      fontSize = 19.sp,
      fontWeight = FontWeight.Medium,
    )
  }

}

@Preview
@Composable
fun NoteDetailPreview() {
  MultiScreenResizeablAppTheme {
    NoteDetailComposable()
  }
}
