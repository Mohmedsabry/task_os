import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.ui.theme.CustomColor


@Composable
fun StateToggle(
    modifier: Modifier=Modifier,
    selectedScreen:(stateText:String)->Unit
) {
    val states = listOf(
        "Convert",
        "Compare"
    )
    var selectedOption by remember {
        mutableStateOf(states[0])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Card(
        shape = RoundedCornerShape(38.dp), modifier = modifier
            .height(160.dp)
            .padding(top = 60.dp, start = 30.dp, end = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(CustomColor.lightGray), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(5.dp)
            ) {

                states.forEach { text ->

                        Text(
                            text = text,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = CustomColor.textBlack,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(20.dp))
                                .clickable {
                                    onSelectionChange(text)
                                    selectedScreen.invoke(text)
                                }
                                .background(
                                    if (text == selectedOption) {
                                        Color.White
                                    } else {
                                        CustomColor.lightGray
                                    }
                                )
                                .padding(10.dp)
                                .weight(1f)
                        )
                    Spacer(modifier = Modifier.width(10.dp))
                    }

                    }

                }
            }
        }
