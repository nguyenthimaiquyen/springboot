$(document).ready(function () {
    toastr.options.timeOut = 2500; //2.5s

    let chosenFile = null;

    //validate
    const validator = $("#product-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "price": {
                required: true,
                min: 1
            },
            "description": {
                maxlength: 1000
            },
            "image": {
                required: true,
            }
        },
        messages: {
            "name": {
                required: "Name is required",
                maxlength: "Name must be less than 255 characters"
            },
            "price": {
                required: "Price is required",
                min: "Price must be positive number"
            },
            "description": {
                maxlength: "Description must be less than 1000 characters"
            },
            "image": {
                required: "Image is required"
            }
        }
    });

    $('#image').click(function (event) {
        const tempFiles = event.target.files;
        if (!tempFiles || tempFiles.length === 0) {
            return;
        }
        console.log(event.target.files[0].data);
        chosenFile = tempFiles[0];

        const imageBlob = new Blob([chosenFile], {type: chosenFile.type});
        const imageUrl = URL.createObjectURL(imageBlob);
        $("#avatar").attr("src", imageUrl);
    });

    //open modal to create a product
    $('.create-product-btn').click(function () {
        $('#product-modal #save-product-btn').attr("action-type", "CREATE");
        $('#product-modal').modal('show');
    });

    //open modal to update a product
    $('.update-product-btn').click(async function (event) {
        //call api lên java và lấy dữ liệu
        const updateProductId = parseInt($(event.currentTarget).attr("product-id"));
        let product = null;
        await $.ajax({
            url: "/admin/products/" + updateProductId,
            type: "GET",
            success: function (data) {
                product = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("There have been errors, please try again!");
            }
        });

        if (!product) {
            toastr.error("There have been errors, please try again!")
            return;
        }
        //đổ dữ liệu vào form
        $('#product-form #name').val(product.name);
        $('#product-form #price').val(product.price);
        $('#product-form #description').val(product.description);
        $('#product-form #image').val(product.image);

        $('#product-modal #save-product-btn').attr('action-type', "UPDATE");
        $('#product-modal #save-product-btn').attr("product-id", updateProductId);
        $('#product-modal').modal("show");
    });

    //create or update student
    $('#save-product-btn').click(function (event) {
        //validate
        const isValidForm = $('#product-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const productId = $(event.currentTarget).attr("product-id");
        //lấy dữ liệu từ form
        const formData = $('#product-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        const method = actionType === "CREATE" ? "POST" : "PUT";
        if (method === "PUT") {
            requestBody["id"] = productId;
        }
        //call api lên backend
        $.ajax({
            url: "/admin/products",
            type: method,
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new product successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
        $("#product-modal #save-product-btn").attr("action-type", "");
        $('#product-modal #save-product-btn').attr("product-id", "");
    });

    //reset form
    $('#product-modal').on('hidden.bs.modal', function () {
        $("#product-modal #save-product-btn").attr("action-type", "");
        $('#product-modal #save-product-btn').attr("product-id", "");
        $('#product-form').trigger("reset");
        $('#product-form input').removeClass("error");
        validator.resetForm();
    });



});