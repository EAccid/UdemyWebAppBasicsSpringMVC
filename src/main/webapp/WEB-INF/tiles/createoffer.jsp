<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

   <!--

    function onDeleteClick() {
        var doDelete = confirm("Are you sure you want delete this offer?");

        if (doDelete  == false) {
            event.preventDefault();
        }
    }

    function onReady() {
        $("#delete").click(onDeleteClick);
    }

    $(document).ready(onReady);

    //-->

</script>

<sform:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">

    <sform:input type="hidden" name="id" path="id"/>

    <table class="formtable">

        <tr>
            <td class="label">Your offer:</td>
            <td>
                <sform:textarea class="control" path="text" name="text" rows="10" cols="10"/><br/>
                <sform:errors path="text" cssClass="error"></sform:errors>
            </td>
        </tr>

        <tr>
            <td class="label"></td>
            <td><input class="control" value="Save advert" type="submit"></td>
        </tr>


        <c:if test="${offer.id !=0}">

            <tr>
                <td class="label"></td>
                <td>&nbsp;</td>
            </tr>

            <tr>
                <td class="label"></td>
                <td><input class="delete control" name="delete"
                           id="delete" value="Delete this offer" type="submit"></td>
            </tr>

        </c:if>

    </table>

</sform:form>

