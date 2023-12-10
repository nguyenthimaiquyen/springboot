$(document).ready(function () {
    toastr.options.timeOut = 2500; //2.5s

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "Phone must be 10 characters, start with zero");

    $.validator.addMethod("emailFormat", function (value, element) {
        return this.optional(element) || /^[A-Za-z0-9+_.-]+@(.+)$/i.test(value);
    }, "Please enter a valid email address");

    //validate
    const validator = $("#appointment-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "phone": {
                required: true,
                vietnamesePhone: true
            },
            "email": {
                required: true,
                maxlength: 150,
                emailFormat: true
            },
            "content": {
                required: true,
                maxlength: 1000
            }
        },
        messages: {
            "name": {
                required: "Name is required",
                maxlength: "Name must be less than 255 characters"
            },
            "phone": {
                required: "Phone is required",
                vietnamesePhone: "Phone must be 10 characters, start with zero"
            },
            "email": {
                required: "Email is required",
                maxlength: "Email must be less than 150 characters",
                emailFormat: "Please enter a valid email address"
            },
            "content": {
                required: "Message is required",
                maxlength: "Message must be less than 1000 characters"
            }
        }
    });

    $('#send-appointment-btn').click(function (event) {
        //validate
        const isValidForm = $('#appointment-form').valid();
        if (!isValidForm) {
            return;
        }
        //open modal confirmation
        $('#appointment-confirmation-modal').modal('show');
    });


    $('#confirm-appointment-btn').click(function () {
        //lấy dữ liệu từ form
        const formData = $('#appointment-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        console.log(formData)
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        console.log(requestBody)
        //call api lên backend
        $.ajax({
            url: "/admin/appointments",
            type: "POST",
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Create a new appointment successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });

    });

});