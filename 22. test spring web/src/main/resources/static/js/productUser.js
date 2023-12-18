$(document).ready(function () {

    toastr.options.timeOut = 2500; //2.5s

    //bắt sự kiện người dùng click nút buy now
    $('.buy-product').click(function () {
        $('#buy-product-modal').modal('show');
    });

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "Số điện thoại phải là dãy 10 ký tự số bắt đầu bằng số 0");

    $.validator.addMethod("emailFormat", function (value, element) {
        return this.optional(element) || /^[A-Za-z0-9+_.-]+@(.+)$/i.test(value);
    }, "Please enter a valid email address");

    //validate buy-product-modal
    const validatorUserSide = $("#buy-product-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "email": {
                required: true,
                maxlength: 150,
                emailFormat: true
            },
            "phone": {
                required: true,
                vietnamesePhone: true
            }
        },
        messages: {
            "name": {
                required: "Name is required",
                maxlength: "Name must be less than 255 characters"
            },
            "email": {
                required: "Email is required",
                maxlength: "Email must be less than 150 characters",
                emailFormat: "Please enter a valid email address"
            },
            "phone": {
                required: "Phone is required",
                vietnamesePhone: "Phone must be 10 characters, start with zero"
            }
        }
    });

    $('#save-buy-product-btn').click(function () {
        //validate
        const isValidForm = $('#buy-product-form').valid();
        if (!isValidForm) {
            return;
        }

        //lấy dữ liệu từ form
        const formData = $('#buy-product-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        //call api lên backend
        $.ajax({
            url: "/orders",
            type: "POST",
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("You have successfully ordered!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });

    });

    //reset form
    $('#buy-product-modal').on('hidden.bs.modal', function () {
        $('#buy-product-modal').trigger("reset");
        $('#buy-product-modal input').removeClass("error");
        validatorUserSide.resetForm();
    });

});