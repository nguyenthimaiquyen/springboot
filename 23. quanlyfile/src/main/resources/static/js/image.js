$(document).ready(function () {

    toastr.options.timeOut = 2500; // 2.5s

    let userId = -1;
    let deleteImageId = -1;
    let chosenFile = [];
    const defaultImg = "/images/default.png";

    //show modal to delete a file
    $('.delete-image-btn').click(function (event) {
        deleteImageId = parseInt($(event.currentTarget).attr("image-id"));
        $('#file-delete-modal').modal('show');
    });

    //delete a file
    $('#delete-image-btn').click(function () {
        $.ajax({
            url: "/api/v1/files/" + deleteImageId,
            type: "DELETE",
            success: function (data) {
                toastr.success("Delete a subject successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //validate
    const validator = $("#file-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "type": {
                required: true,
            },
            "createdAt": {
                required: true,
            },
            "image": {
                required: true,
            }
        },
        messages: {
            "type": {
                required: "Type is required",
            },
            "createdAt": {
                required: "Created date is required",
            },
            "image": {
                required: "Image is required"
            }
        }
    });

    $('#img-show').click(() => {
        $('#image').click();
    });

    //open modal to upload file
    $('.upload-file-btn').click(function () {
        $('#img-show').attr('src', defaultImg);
        $('#file-upload-modal').modal('show');
    });

    $('#image').change(function (event) {
        const tempFiles = event.target.files;
        if (!tempFiles || tempFiles.length === 0) {
            return;
        }
        chosenFile = tempFiles[0];

        const imageBlob = new Blob([chosenFile], {type: chosenFile.type});
        const imageUrl = URL.createObjectURL(imageBlob);
        $('#img-show').attr("src", imageUrl);
    });

    //save file
    $('#save-file-btn').click(function (event) {
        //validate
        const isValidForm = $('#file-form').valid();
        if (!isValidForm) {
            return;
        }

        //lấy dữ liệu từ form
        const Data = $('#file-form').serializeArray();
        if (!Data || Data.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < Data.length; i++) {
            requestBody[Data[i].name] = Data[i].value;
        }
        console.log(requestBody);
        const formData = new FormData();
        formData.append("image", chosenFile, chosenFile.name);
        console.log(formData)
        formData.append("fileRequest", JSON.stringify(requestBody));
        console.log(formData)
        //call api lên backend
        $.ajax({
            url: "/api/v1/files/" + userId,
            type: "POST",
            data: formData, //dữ liệu được gửi vào trong body của HTTP
            contentType: false, //NEEDED, DON'T OMIT THIS
            processData: false, //NEEDED, DON'T OMIT THIS
            success: function (data) {
                toastr.success("Create a new file successfully!");
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
    $('#file-upload-modal').on('hidden.bs.modal', function () {
        $('#file-form').trigger("reset");
        $('#file-form input').removeClass("error");
        validator.resetForm();
    });

    //lấy id của user từ URL hiện tại
    const currentUrl = window.location.href;
    const userIdMatch = currentUrl.match(/\/users\/(\d+)\/files/);
    if (userIdMatch) {
        userId = userIdMatch[1];
        console.log("User ID: ", userId);
    } else {
        console.log("Path variable not found in the URL");
    }

    //set url mới khi thẻ select thay đổi
    $('#image-page-size').change(function (event) {
        const pageSize = event.target.value;
        window.location.href = ('/users/' + userId + '/files?pageSize=' + pageSize + '&currentPage=0');
    });

});