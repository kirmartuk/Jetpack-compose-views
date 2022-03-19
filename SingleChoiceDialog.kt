
@Composable
fun SingleChoiceDialog(
  navController: NavController,
  singleChoiceDialogVo: SingleChoiceDialogVo,
  setSelectedItem: (String) -> Unit
) {
  val (selectedItemIndex, setSelectedItemIndex) = remember {
    mutableStateOf(singleChoiceDialogVo.indexOfSelectedItem)
  }
  AlertDialog(
    title = { Text(text = singleChoiceDialogVo.title) },
    text = {
      RadioItems(singleChoiceDialogVo.radioOptions, selectedItemIndex, setSelectedItemIndex)
    },
    onDismissRequest = {
      navController.popBackStack()
    },
    dismissButton = {
      TextButton(onClick = {
        navController.popBackStack()
      }) {
        Text(text = stringResource(id = R.string.dialog_cancel))
      }
    },
    confirmButton = {
      TextButton(onClick = {
        setSelectedItem(singleChoiceDialogVo.radioOptions[selectedItemIndex])
      }) {
        Text(text = stringResource(id = R.string.dialog_ok))
      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioItems(items: List<String>, selectedItemIndex: Int, setIndexOfSelected: (Int) -> Unit) {
  Column(modifier = Modifier.fillMaxWidth()) {
    items.forEachIndexed { index, text ->
      Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .clickable {
          setIndexOfSelected(index)
        }) {
        RadioButton(
          selected = (index == selectedItemIndex),
          onClick = {
            setIndexOfSelected(index)
          }
        )
        Text(
          text = text
        )
      }
    }
  }
}
