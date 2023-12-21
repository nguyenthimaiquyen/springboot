$(document).ready(function () {
    toastr.options.timeOut = 2500; //2.5s

    let chosenFile = [];
    const defaultImg = "/images/default.png";

    //validate
    const validatorAdminSide = $("#product-form").validate({
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

    $('#product-img-show').click(() => {
        $('#product-image').click();
    });

    //open modal to create a product
    $('.create-product-btn').click(function () {
        $('#product-img-show').attr('src', defaultImg);
        $('#product-modal #save-product-btn').attr("action-type", "CREATE");
        $('#product-modal').modal('show');
    });

    $('#product-image').change(function (event) {
        const tempFiles = event.target.files;
        if (!tempFiles || tempFiles.length === 0) {
            return;
        }
        chosenFile = tempFiles[0];

        const imageBlob = new Blob([chosenFile], {type: chosenFile.type});
        const imageUrl = URL.createObjectURL(imageBlob);
        $('#product-img-show').attr("src", imageUrl);
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
        const Data = $('#product-form').serializeArray();
        if (!Data || Data.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < Data.length; i++) {
            requestBody[Data[i].name] = Data[i].value;
        }
        const method = actionType === "CREATE" ? "POST" : "PUT";
        if (method === "PUT") {
            requestBody["id"] = productId;
        }
        const formData = new FormData();
        formData.append("image", chosenFile, chosenFile.name);
        formData.append("productRequest", JSON.stringify(requestBody));
        //call api lên backend
        $.ajax({
            url: "/admin/products",
            type: method,
            data: formData, //dữ liệu được gửi vào trong body của HTTP
            contentType: false, //NEEDED, DON'T OMIT THIS
            processData: false, //NEEDED, DON'T OMIT THIS
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new product successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
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
        validatorAdminSide.resetForm();
    });


    //set url mới khi thẻ select thay đổi
    $('#product-page-size').change(function (event) {
        const pageSize = event.target.value;
        window.location.href = ('/admin?pageSize=' + pageSize + '&currentPage=0');
    });


});