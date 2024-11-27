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

**Explicación** :smile:
Esta es la UI principal, evidentemente no voy a poner todo el codigo de la UI, pero si la principal para que se vea como se va haciendo un escalado de la aplicación.
Podemos observar lo siguiente:
1. Esta UI es la principal, la cual tiene un @Composable para indicar que es una interfaz de usuario
2. Por parámetro le vamos a pasar el viewModel para que acceda a los métodos del viewmodel
3. Podemos observar diferentes variables que están observando a un metodo del viewmodel
4. Esto se hace ya que si no ponemos algun observador, remember o algo que recuerde a la UI que tiene que redibujar algo, esto no pasará y se mantendrá en el mismo estado que cuando inició la aplicación
5. El Objeto modifier lo usamos para rediseñar la UI y ponerle diferentes parámetros según como queremos que quede la interfaz
6. El escalado de la aplicaicón lo podemos observar mediante que en los elementos como Row, dentro llamamos a otras composables

### 5. Explicación del viewModel: :smile:

```bash
class MyViewModel(): ViewModel() {

    var random = Random

    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    private val _recordLiveData = MutableLiveData<Int>()

fun winOrLose(lista_Random: MutableList<Int>, listaColores: MutableList<Int>){
        if(listaColores.size <= lista_Random.size){
            auxWinOrLose(lista_Random, listaColores)
        }
    }

    private fun auxWinOrLose(lista_Random:MutableList<Int>,listaColores:MutableList<Int>){
        if(lista_Random == listaColores){
            onWin(listaColores)
            Log.d("random", "ganaste")
            Log.d("randomRe", getRecord().toString())
            Log.d("randomAc", getAciertos().toString())
            Log.d("randomRor", getRondas().toString())
        }
        else if (lista_Random.subList(0, listaColores.size) == listaColores){
            Log.d("TAG", "CORRECTO")
        }
        else{
            Log.d("random", "perdiste")
            onLose(listaColores)
        }
    }
```
En el código anterior podemos obsevar que es todo lógica de la aplicación. No podemos tener ninguna composable en el viewModel y tiene que cumplir lo siguiente:
1. El viewModel es el responsable de la comunicación entre la UI y los datos(model)
2. En el viewModel se maneja la lógica del negocio
3. Si queremos que un cambio del viewModel se refleje en la UI, debemos declarar esa variable coo MutableLiveData y observarla en la UI
4. La clase viewModel debe de heredar sí o sí de ViewModel()

### 6.  Explicación de los Datos :smile:
```bash
#Ejemplo de Singleton
object Datos {

    //los aciertos que lleva el usuario
    var aciertos = 0
    //las rondas que lleva el usuario
    var rondas = 0
    // los numeros randoms para meter en la lista de la maquina
    var numRandom = 0
    //el record maximo del usuario
    var record = 0
    //la lista de numeros randoms de la maquina
    val listaNumerosRandom : MutableList<Int> = mutableListOf()


    var listaColores : MutableList<Int> = mutableListOf()

}

#Ejemplo de clase enum con los estados
enum class Estados(val startActivo: Boolean, val botonesColoresActivos:Boolean){

    /**
     * Estados de la aplicación
     * 1. Inicio -> cuando se inicia la aplicación y aun no le dimos al start
     * 2. Generando -> cuando la maquina genera los numeros randoms cuando le das al start y salen las toast informadoras
     * 3. Advininando -> cuando el usuario teclea os botones de colores para adivinar los numeros
     */
    INICIO(startActivo = true, botonesColoresActivos = false),
    ADIVINANDO(startActivo = false, botonesColoresActivos = true),

}
```
En el ejemplo anterior observamos algunas cosas:
1. Los datos de la aplicación no tiene ningún tipo de lógica, solo contiene datos
2. La clase object es un singleton que podemos instanciar muchas veces pero será el mismo objeto
3. Con la clase enum podemos manejar estados de la aplicación

### 7. Como funciona el programa :smile:
1. El usuario entra en la aplicación y los botones de colores están deshabilitados
2. En ese momento nos encontramos en el estado inicio, este estado hace que el start esté habilitado y los botones de colores no
3. Cuando el usuario pulsa ell botón der start, pasamos a otro estado, que es el generando, en el cual el start está deshabilitado y los botones de colores también
4. Se genera un numero aleatorio entre el 1 y el 4 y se añade a una lista
5. Una vez se genera el número, pasamos al estado Adivinando, en el cual el start sigue deshabilitado y los botones de colores se habilitan
6. Tenemos 4 colores, cada 1 representa un numero del 1 al 4
7. El usuario debe de adivinar el numero.
8. La pista que se le da al usuario es que cuando le da al start, se ilumina el color el cual tiene que pulsar
9. SI el usuario acierta, se aumentan las rondas, aciertos
10. si el acierto es mayor que el record, el record será igual al acierto
11. En la siguiente ronda, se aumenta el numero de la secuencia, es decir, ahora el usuario tendrá que pulsar un botón de color más que en la ronda anterior y así sucesivamente
12. Si el usuario gana, se vuelve al estado Inicio
13. Si el usuario pierde, las rondas y aciertos se quedan en 0, pero el recors se mantiene. Se vuelve igualmente al estado Inicio
14. La lógica la manejamos en los onclick de los botones de colores y en el boton start
15. Cuando el usuario pulsa el start, se dispara el metodo de crear random y se pasa a otro estado
16. Cuando el usuario pulsa algún botón de color, se comprueba si se acertó o no

```bash
#Ejemplo de onclick con los botones de colores
 var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.botonesColoresActivos) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.botonesColoresActivos
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.addColor(colorValor,listaColores, lista_Random)
        }
```
En el ejemplo anterior, observamos que en el onclick, estamos llamando a un metodo del viewModel que añade un color


### 8. Implementaciones adicionales a considerar en el futuro :smile:
Que mejoras hacer en el futuro:
1. implementar base de datos para guardar el record del usuario cuando salga de la aplicación
2. implementar autenticación para entrar en el juego y guardar el alias junto al record del jugador
3. Hacer que los botones de colores estén deshabilitados mientras están parpadeando
4. Mejorar la apariencia de la aplicación
