/**
 * Created by Arsenii on 27.04.2016.
 */


$('document').ready(function () {

    $('#sendava').click(function () {
        var formData = new FormData();
        formData.append('file', $('input[type=file]')[0].files[0]);
        console.log("form data " + formData);
        $.ajax({
            url : '/api/file/upload',
            data : formData,
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {
                console.log(data);
            },
            error : function(err) {
                console.log(err);
            }
        });
    });
});
