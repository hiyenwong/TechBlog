# Testing Python Code with Visual Studio Code

Visual Studio Code (VS Code) is a powerful editor that supports many programming languages, including Python. One of the features that makes it a great tool for developers is its support for testing. In this post, we'll walk through how to use VS Code to test Python code.

## .vscoding/settings.json

```json
{
    "python.unitTest.pyTestArgs": [
        "-v"
    ],
    "python.unitTest.unittestArgs": [
        "-v"
    ],
    "python.unitTest.nosetestArgs": [
        "-v"
    ],
    "python.unitTest.pyTestEnabled": true,
    "python.unitTest.unittestEnabled": ,
}
```

## pytest.ini file

```ini
[pytest]
testpaths = tests
python_files = test_*.py
addopts = -ra -q
```

## Setting Up Your Environment

Before you can start testing, you need to set up your environment. This includes installing Python, VS Code, and the Python extension for VS Code.

1. Download and install Python from the official website.
2. Download and install VS Code from the official website.
3. Open VS Code, go to the Extensions view (View -> Extensions), and search for the Python extension. Click Install.

## Writing Tests

### using unittest

Python's built-in `unittest` module makes it easy to write tests for your code. Here's an example of a simple test:

```python
import unittest

class TestSum(unittest.TestCase):
    def test_sum(self):
        self.assertEqual(sum([1, 2, 3]), 6, "Should be 6")

if __name__ == '__main__':
    unittest.main()
```

#### Running Tests in VS Code

Once you've written your tests, you can run them directly in VS Code.

- Open the Command Palette (View -> Command Palette or Ctrl+Shift+P).
- Type "Python: Discover Tests" and press Enter. VS Code will automatically discover your tests.
- To run the tests, open the Command Palette again, type "Python: Run All Tests", and press Enter.
- VS Code will run your tests and display the results in the Python Test Log output panel.

#### Conclusion

Testing is a crucial part of software development, and VS Code makes it easy to test your Python code. With the Python extension, you can write, run, and debug your tests without leaving your editor.

This is a basic guide and may need to be adjusted based on the specifics of your Python project and testing needs.

### using pytest

创建pytest测试文件，并在文件中编写测试代码。

```python
def test_sum():
    assert sum([1, 2, 3]) == 6
```

需要遵循几个规则, 测试函数必须以test_开头，参数必须是test_开头的函数, 类必须使用Test开头, 类中的函数必须以test_开头

```python
def test_sum():
    assert sum([1, 2, 3]) == 6
```

#### Running Tests in VS Code by pytest

- Open the Command Palette (View -> Command Palette or Ctrl+Shift+P).
- Type "Python: Discover Tests" and press Enter. VS Code will automatically discover your tests.
- To run the tests, open the Command Palette again, type "Python: Run All Tests", and press Enter.
- VS Code will run your tests and display the results in the Python Test Log output panel.

- Open the Command Palette (View -> Command Palette or Ctrl+Shift+P).
- Type "Python: Discover Tests" and press Enter. VS Code will automatically discover your tests.

[EOF]
