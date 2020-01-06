<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<form id="upload" class="content-center" enctype="multipart/form-data">
    <label>名称：<input name="fileName"></label><br>
    <label>文件：<input type="file" name="uploadFile"></label><br>
    <input type="button" value="上传" onclick="fileSubmit()">
</form>
<script>
    function fileSubmit() {
        const form = new FormData(document.getElementById("upload"));
        $.ajax({
            url: "/upload",
            data: form,
            type: "post",
            processData: false,
            contentType: false,
            success: (data) => {
                alert(data);
            }
        });
    }
</script>
</body>
</html>
