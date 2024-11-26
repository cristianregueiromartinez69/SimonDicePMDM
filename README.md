# SIMON DICE HECHO EN ANDROID STUDIO :relaxed:
**APARTADOS**
1. En que consiste el juego
2. Objetivos a realizar
3. implementación del patrón MVVM
4. Explicación de las UI
5. Explicación del ViewModel
6. Explicación de los Datos
7. Como funciona el programa
8. Implementaciones adicionales a considerar en el futuro


### 1. En que consiste el juego :smile:
El juego del simón dice que he hecho en android studio consiste en lo siguiente:
- Tenemos un panel central con 4 colores
- El juego no empieza hasta que el usuario pulsa el start
- Se genera una secuencia de 1 color cuando el usuario pulsa el star
- Si el usuario acierta, en la siguiente ronda se incrementa en 1 la secuencia
- El juego no acaba hasta que el usuario pierde

### 2. Objetivos a realizar :smile:
- Realizar una secuencia de manera que si el usuario introduce un solo número mal, pierde automáticamente
- Realizar el iluminado de colores mediante las curutinas
- Implementar el patrón de diseño MVVM
- Refactorizar de la mejor manera el código

### 3. implementación del patrón MVVM :smile:
Nos vamos al android studio y vamos a tener la siguiente estructura
![simondice1](https://github.com/user-attachments/assets/72e4a5d1-c1ce-464e-9241-09e48cc68f11)

Como podemos ver, tenemos la estructura en paquetes diferentes, ahora vamos a entrar en las clases y a explicar el funcionamiento de cada una

### 4. Explicación de las UI :smile:
```bash
#Vamos a coger de ejemplo a la UI principal que será llamada desde la MainActivity
@Composable
fun myApp(viewModel: MyViewModel) {

    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val rondas by viewModel.rondasLiveData.observeAsState(viewModel.getRondas())
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())



    var lista_colores = remember { mutableStateListOf<Int>() }

    val colorRojo by viewModel.colorRojoLiveData.observeAsState(viewModel.getColorRed())
    val colorVerde by viewModel.colorVerdeLiveData.observeAsState(viewModel.getColorGreen())
    val colorAzul by viewModel.colorAzulLiveData.observeAsState(viewModel.getColorBlue())
    val colorAmarillo by viewModel.colorAmarilloLiveData.observeAsState(viewModel.getColorYellow())



    Box (modifier = Modifier
        .fillMaxSize()
    ){
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column {
            RecordMaximo(record)
            showAciertos(aciertos)
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.ROJO.valorColor,
                    colorRojo
                )
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.VERDE.valorColor, colorVerde)
            }

            Row {
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AZUL.valorColor, colorAzul)
                buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AMARILLO.valorColor, colorAmarillo)
            }

            showRondas(rondas)

            showButtonStart(viewModel)



        }
    }

}
```

**Explicación** :relaxed:
Esta es la UI principal, evidentemente no voy a poner todo el codigo de la UI, pero si la principal para que se vea como se va haciendo un escalado de la aplicación.
Podemos observar lo siguiente:
1. Esta UI es la principal, la cual tiene un @Composable para indicar que es una interfaz de usuario
2. Por parámetro le vamos a pasar el viewModel para que acceda a los métodos del viewmodel
3. Podemos observar diferentes variables que están observando a un metodo del viewmodel
4. Esto se hace ya que si no ponemos algun observador, remember o algo que recuerde a la UI que tiene que redibujar algo, esto no pasará y se mantendrá en el mismo estado que cuando inició la aplicación
5. El Objeto modifier lo usamos para rediseñar la UI y ponerle diferentes parámetros según como queremos que quede la interfaz
6. El escalado de la aplicaicón lo podemos observar mediante que en los elementos como Row, dentro llamamos a otras composables


