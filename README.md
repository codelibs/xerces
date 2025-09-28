# Xerces2-J

A high-performance, fully compliant XML parser implementing XML 1.0/1.1, XML Schema 1.0, DOM Level 3, XInclude, JAXP, SAX, and StAX APIs. This is a fork maintained by [CodeLibs](https://www.codelibs.org/) based on Apache Xerces.

## 🚀 Key Features

- **Complete XML Support**: XML 1.0 and XML 1.1 specification compliance
- **Schema Validation**: Full XML Schema 1.0 processor with experimental 1.1 support
- **Multiple APIs**: DOM Level 3 Core/Load/Save, SAX, StAX, JAXP integration
- **XInclude Support**: Complete XML Inclusions (XInclude) W3C Recommendation implementation
- **Modular Architecture**: Xerces Native Interface (XNI) for building custom parser components
- **High Performance**: Optimized for speed and memory efficiency
- **Standards Compliant**: Reference implementation quality with comprehensive test suite

## 🛠 Tech Stack

- **Java**: 17+ (compiled with release flag for compatibility)
- **Build System**: Apache Maven 3.x
- **Testing**: JUnit 5 with Vintage engine for backward compatibility
- **Dependencies**: XML Resolver (optional)

## 📋 Prerequisites

- **JDK 17 or higher** - Required for building and running
- **Apache Maven 3.x** - For build management
- **Git** - For source code management

## ⚡ Quick Start

### Installation

```bash
# Clone the repository
git clone https://github.com/codelibs/xerces.git
cd xerces

# Build the project
mvn clean install

# Skip tests for faster build
mvn clean install -DskipTests
```

### Maven Dependency

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>org.codelibs.xerces</groupId>
    <artifactId>xerces</artifactId>
    <version>3.0.0-SNAPSHOT</version>
</dependency>
```

## 🔧 Build Commands

### Core Build Operations

```bash
# Compile source code
mvn compile

# Build JAR file
mvn package

# Run all tests (433 tests)
mvn test

# Generate Javadocs
mvn javadoc:javadoc

# Clean build artifacts
mvn clean

# Complete build with tests
mvn clean install
```

### Development Commands

```bash
# Format code according to CodeLibs standards
mvn formatter:format

# Generate test coverage report
mvn jacoco:report

# Check license headers
mvn license:check

# Update license headers
mvn license:format
```

### Deployment

```bash
# Deploy to Maven Central (requires proper credentials)
./deploy.sh
```

## 📖 Usage Examples

### Basic XML Parsing with DOM

```java
import org.codelibs.xerces.parsers.DOMParser;
import org.w3c.dom.Document;

DOMParser parser = new DOMParser();
parser.parse("example.xml");
Document document = parser.getDocument();
```

### SAX Parsing

```java
import org.codelibs.xerces.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;

SAXParser parser = new SAXParser();
parser.setContentHandler(new DefaultHandler() {
    @Override
    public void startElement(String uri, String localName,
                           String qName, Attributes attributes) {
        System.out.println("Element: " + qName);
    }
});
parser.parse("example.xml");
```

### XML Schema Validation

```java
import org.codelibs.xerces.parsers.DOMParser;
import org.codelibs.xerces.impl.Constants;

DOMParser parser = new DOMParser();
parser.setFeature(Constants.SAX_FEATURE_PREFIX + Constants.VALIDATION_FEATURE, true);
parser.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE, true);
parser.parse("document.xml");
```

### XInclude Processing

```java
import org.codelibs.xerces.parsers.XIncludeParserConfiguration;
import org.codelibs.xerces.parsers.DOMParser;

XIncludeParserConfiguration config = new XIncludeParserConfiguration();
DOMParser parser = new DOMParser(config);
parser.parse("document-with-xincludes.xml");
```

## 🏗 Project Structure

```
src/main/java/org/codelibs/xerces/
├── parsers/          # High-level parser implementations
├── impl/             # Core parser implementation
│   ├── dtd/         # DTD validation
│   ├── dv/          # Datatype validation (Schema)
│   ├── xs/          # XML Schema processing
│   ├── xpath/       # XPath subset for Schema
│   └── validation/  # Validation framework
├── xni/             # Xerces Native Interface (XNI)
├── dom/             # DOM Level 3 implementation
├── jaxp/            # JAXP API integration
├── stax/            # StAX API integration
├── util/            # Utility classes
├── xinclude/        # XInclude processing
└── xs/              # XML Schema API
```

### Sample Code

The project includes comprehensive examples in `src/sample/java/`:

- **SAX Examples**: Event-driven parsing
- **DOM Examples**: Tree-based document manipulation
- **JAXP Examples**: Standard Java API usage
- **XNI Examples**: Low-level parser component usage
- **Schema Examples**: Validation and type information access

## 🧪 Testing

The project includes 433 comprehensive tests covering:

- **Core Parser Functionality**: XML 1.0/1.1 parsing
- **Schema Validation**: XML Schema 1.0 compliance
- **API Compatibility**: SAX, DOM, StAX, JAXP integration
- **XInclude Processing**: Inclusion mechanism testing
- **Performance Tests**: Memory and speed benchmarks

```bash
# Run all tests
mvn test

# Run with coverage report
mvn clean test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

## 🔧 Configuration Options

### Parser Features

```java
// Enable schema validation
parser.setFeature("http://apache.org/xml/features/validation/schema", true);

// Enable XInclude processing
parser.setFeature("http://apache.org/xml/features/xinclude", true);

// Set security features
parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
```

### System Properties

```bash
# Enable schema validation by default
-Dorg.codelibs.xerces.xni.parser.XMLParserConfiguration=org.codelibs.xerces.parsers.XML11Configuration

# Set entity resolver
-Dorg.xml.sax.driver=org.codelibs.xerces.parsers.SAXParser
```

## 🚀 Performance

Xerces2-J is optimized for:

- **High Throughput**: Efficient XML parsing for large documents
- **Low Memory Footprint**: Minimal memory usage during processing
- **Scalability**: Suitable for high-load server environments
- **Compliance**: Full standards compliance without performance sacrifice

## 🐛 Troubleshooting

### Common Issues

**OutOfMemoryError during large document parsing:**
```bash
# Increase heap size
export MAVEN_OPTS="-Xmx2g"
mvn test
```

**Schema validation not working:**
```java
// Ensure both validation and schema features are enabled
parser.setFeature(Constants.SAX_FEATURE_PREFIX + Constants.VALIDATION_FEATURE, true);
parser.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE, true);
```

**Build failures on Java 17+:**
```bash
# Ensure JAVA_HOME points to JDK 17+
export JAVA_HOME=/path/to/jdk-17
mvn --version
```

## 🤝 Development

### Setting Up Development Environment

1. **Fork and clone** the repository
2. **Import** into your IDE as a Maven project
3. **Configure** JDK 17+ as project SDK
4. **Run tests** to verify setup: `mvn test`

### Code Style

The project follows CodeLibs formatting standards:

```bash
# Format code before committing
mvn formatter:format

# Check formatting
mvn formatter:validate
```

### Building Documentation

```bash
# Generate API documentation
mvn javadoc:javadoc

# View generated docs
open target/site/apidocs/index.html
```

## 📄 License

Licensed under the [Apache License, Version 2.0](LICENSE). See the `LICENSE` file for details.

This project includes components from:
- Apache Xerces (Apache License 2.0)
- XML Resolver (Apache License 2.0)

## 🗂 Related Projects

- [Apache Xerces2-J](http://xerces.apache.org/xerces2-j/) - Original Apache project
- [XML Commons](http://xml.apache.org/commons/) - XML API commons
- [Apache XML Project](http://xml.apache.org/) - XML tools and libraries

