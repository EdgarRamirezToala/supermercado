# supermercado

#### ProductoDao.java
#### Line 34

#### cambiar ruta del archivo de productos.json

```java
@Service
public class ProductoDao implements ProductoService {
	int pid = 0;

	@Value("${jsonplaceholder.url}")
	private String url;

	File file = new File(
			"C:\\SpringCloud\\workspace\\springboot-service-supermercado\\src\\main\\resources\\static\\db\\productos.json");
	HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();
	ObjectMapper mapper = new ObjectMapper();
```