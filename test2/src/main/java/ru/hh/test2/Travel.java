// 2. Программа в составе своём содержит модуль для рассчёта стоимости путешествия, на основе используемого автомобиля и множества других различных данных.
// Что в этом коде вам кажется неправильным, модифицируте его таким образом, чтобы в дальнейшем разработчик мог добавлять новую марку автомобиля с минимальными трудозатратами

package ru.hh.test2;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;
import org.apache.commons.jelly.*;
import org.xml.sax.InputSource;

class Main {
    public static void main(String[] args)  {
        
        new Travel("Ford").doTravel();
        new Travel("Opel").doTravel();
    }
}       

// Модели машин вместе с формулами расчета стоимости хранятся в файлах <название модели>.jelly
// Чтобы добавить новую модель, нужно просто добавить новый файлик (при этом необязательно пересобирать проект. В этом плюс по сравнении с наследованием и кучей классов)
public class Travel {
    private String auto;
    private JellyContext ctx = new JellyContext();

    public Travel(String auto) {
      this.auto = auto;
    }

    public void doTravel() {
        
        Writer writer = new StringWriter();
        
        try {
            ctx.runScript(new InputSource(Class.class.getResourceAsStream("/" + auto + ".jelly")), XMLOutput.createXMLOutput(writer)); 
        
            System.out.println(writer.toString());
        }
        catch (JellyException e) {
            System.out.println("Unable to calculate travel for car: " + auto);
        }
    }
    
    // public static для простоты
    public static float someInnerLogic() {
      return new Random().nextFloat() + 1;
    }
}