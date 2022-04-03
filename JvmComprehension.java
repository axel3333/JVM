/* Изначально произойдет загрузка класса через подсистему загрузчиков классов, класс JvmComprehension
Класс пройдет через Application ClassLoader -> Platform ClassLoader -> Bootstrap ClassLoader. Он не будет
найден в Bootstrap ClassLoader, перейдет в Platform ClassLoader, который тоже не найдет и загрузится через
Application ClassLoader
*/
public class JvmComprehension { // В MetaSpace будет создан JvmComprehension.class

    public static void main(String[] args) { // В StackMemory будет создан фрейм main()
        int i = 1;
        // 1 Будет создана переменная i внутри фрейма main()
        Object o = new Object();
        /* 2 Будет создан объект типа Object в разделе памяти heap и в переменную о будет добавлена
        ссылка на данный объект. Переменная о будет помещена в фрейм main()
         */
        Integer ii = 2;
        /* 3 Внутри фрейма main() будет создана переменная ii, в которую будет помещена ссыкла на
        объект, равный 2, который будет сознан в heap
         */
        printAll(o, i, ii);
        /* 4 В StackMemory будет создан новый фрейм printAll() в котором будет создана переменная i
        а также ссылки на объекты ii и о, которые уже находятся в heap
         */
        System.out.println("finished");
        /* 7 Создастся новый фрейм, который выведет инфромацию на экран и удалится из StackMemory.
        После выполнения этой команды main() также будет удален из стека
         */

    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;
        /* 5 Создается объект в heap, ссылка на который будет создана в фрейме printAll() под именем
        uselessVar
         */
        System.out.println(o.toString() + i + ii);
        /* 6 Будет создан новый фрейм в стеке, после чего toString создаст еще один фрейм, который
        отработает и будет убран из стека, передав результат в предыдущий фрейм printAll(), после чего
        он также отработает и передаст результат в фрейм main. После этого фрейм printAll()также будет
        удален из стека
         */
    }

    // При срабатывании сборщика мусора будет удалена переменная uselessVar, которая нигде не используется
}
