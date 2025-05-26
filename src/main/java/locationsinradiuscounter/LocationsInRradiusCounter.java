package locationsinradiuscounter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import locationsinradiuscounter.Containers.InputDataContainer;
import locationsinradiuscounter.Containers.ResultContainer;
import locationsinradiuscounter.Readers.LocationsReader;
import locationsinradiuscounter.Services.LocationsFinder;
import locationsinradiuscounter.Writers.OptimalLocationsWriter;
import locationsinradiuscounter.parameters.ReaderParameters;
import locationsinradiuscounter.parameters.WriterParameters;

/**
 * Приложение считывает входные данные при помощи метода класса LocationsReader, 
 * параметры чтения передаются при помощи экземпляра ReaderParameters. Считанные
 * данные помещаются в экземпляр InputDataContainer и передаются в качестве 
 * аргумента в статик метод findLocationsByNeighborsThenById класса 
 * LocationsFinder, который выполняет последующий подсчет ближайших точек.
 * Метод findLocationsByNeighborsThenById выбирает локации с наибольшим 
 * количеством соседей и возвращает их в виде экземпляра ResultContainer.
 * Метод getLocationsWithMaxNeighbors сласса LocationsNeighborsCounter
 * определяет количество соседей для каждой локации и их в виде ассоциированного 
 * int[], где значение i-го элемента соответствует количеству соседей у i-ой 
 * локации в радиусе R.
 * Количества соседей определяется при помощи одной из двух стратегий 
 * (в зависимости от величины входного файла):
 * 1. Метод класса BruteForceNeighborsCounter - тривиальный O(N^2) алгоритм, 
 * обходит все локации в двойном цикле и определят, находятся ли они на 
 * расстоянии меньшем чем R друг от друга;
 * 2. Метод класса PlaneGridNeighborsCounter - более быстрый алгоритм, разбивает 
 * плоскость, в которой находятся локации, на 2D сетку. Размер ячейки сетки 
 * выбирается таким образом, чтобы окружность радиусом R, проведенная из каждой 
 * локации, не выходила за пределы соседних ячеек сетки. Это позволяет сократить 
 * количество потенциальных соседей до тех, которые располагаются в этой и 
 * соседних ячейках.
 * Выходной файл заполняется при помощи метода класса OptimalLocationsWriter, 
 * праметры записи передаются при помощи экземпляра writerParams.
 */
public class LocationsInRradiusCounter {
    
    public static void main(String[] args) throws IOException {
        ReaderParameters readerParams = new ReaderParameters(
            Config.getInstance().getInputDataPath(),
            StandardCharsets.UTF_8
        );
        InputDataContainer input = LocationsReader.readFromFile(readerParams);
        ResultContainer output = LocationsFinder.findLocationsByNeighborsThenById(
            input,
            Config.getInstance().getOutputLocationsCount()
        );
        WriterParameters writerParams = new WriterParameters(
            Config.getInstance().getOutputDataPath(),
            StandardCharsets.UTF_8,
            false
        );
        OptimalLocationsWriter.writeToFile(output, writerParams);
    }
}
