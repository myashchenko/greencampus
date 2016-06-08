
$('document').ready(function () {

    $('#btnSub').click(function () {
        var $signup = $('#signup-form');
        if ($signup[0].checkValidity()) {
            $.ajax({
                url: '/api/user/',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({
                    'email': $('#inputEmail').val(),
                    'name': $('#inputName').val(),
                    'password': $('#inputPassword').val()
                }),
                success: function (data) {
                    console.log(data);
                    $('#regDone').html('Registration is done.');
                    $('#form-id').html('');
                    setTimeout(function () {
                        window.location = '/login';
                    }, 1000);
                },
                error: function (data) {
                    console.log(data);
                    $('#regDone').html('Registration is false. Email is already taken or your data is false.');
                }

            });
        } else {
            $signup.find(':submit').click()
        }
    });
});
