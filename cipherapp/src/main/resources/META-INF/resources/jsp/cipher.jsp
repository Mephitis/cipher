<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
    <form method="post" modelAttribute="selectedCipher">
    <div style="width:50%">
        <div>
            <span>Select cipher:</span>
            <span>
                <select name="cipher">
                    <c:forEach items="${cipherNames}" var="cipherName">
                        <option value="${cipherName}">${cipherName}</option>
                    </c:forEach>
                </select>
            </span>
        </div>
        <div />
        <div>
            <span>Input</span>
        </div>
        <div>
            <textarea id="input" name="input" value="${input}"  rows="5" cols="50">${input}</textarea>
        </div>
        <div>
            <span>Output</span>
        </div>
        <div>
            <textarea id="output" name="output" value="${output}" disabled="true" rows="5" cols="50">${output}</textarea>
        </div>
        <div>
           <input type="submit"  name="encode" value="Encode" />
           <input type="submit"  name="decode" value="Decode" />
        </div>
    </div>
    </form>
</body>

</html>