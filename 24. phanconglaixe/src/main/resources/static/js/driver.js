$(document).ready(() => {
    toastr.options.timeOut = 2500; // 2.5s

    let deleteDriverId = -1;

    function getDriverLevel() {
        $.ajax({
            url: "/api/v1/drivers/level",
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                const driverLevelSelection = $('#driver-modal #level');
                if (driverLevelSelection.children().length === 0) {
                    if (!data || data.length === 0) {
                        return;
                    }
                    let driverLevelOptions = "";
                    for (let i = 0; i < data.length; i++) {
                        driverLevelOptions += "<option value='" + data[i].code + "'>" + data[i].name + "</option>";
                    }
                    driverLevelSelection.append($(driverLevelOptions));
                }
            },
            error: function (data) {
                toastr.warning(data.responseJSON.error);
            }
        });
    }

    getDriverLevel();
    setInterval(function () {
        getDriverLevel();
    }, 900000); // 15p chạy 1 lần

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "Phone must be 10 characters, start with zero");

    //validate
    const validator = $("#driver-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "address": {
                required: true,
                maxlength: 255
            },
            "phone": {
                required: true,
                vietnamesePhone: true
            },
            "level": {
                required: true,
            }
        },
        messages: {
            "name": {
                required: "Driver's name is required",
                maxlength: "Student's name must be less than 255 characters"
            },
            "address": {
                required: "Address is required",
                maxlength: "Address must be less than 255 characters"
            },
            "phone": {
                required: "Phone is required",
                vietnamesePhone: "Phone must be 10 characters, start with zero"
            },
            "level": {
                required: "Level is required",
            }
        }
    });

    //mở modal tạo mới lái xe
    $('.create-driver-btn').click(function () {
        $('#driver-modal #save-driver-btn').attr("action-type", "CREATE");
        $('#driver-modal').modal('show');
    });

    //open modal to update a driver
    $('.update-driver-btn').click(async function (event) {
        //call api lên java và lấy dữ liệu
        const updateDriverId = parseInt($(event.currentTarget).attr("driver-id"));
        let driver = null;
        await $.ajax({
            url: "/api/v1/drivers/" + updateDriverId,
            type: "GET",
            success: function (data) {
                driver = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("There have been errors, please try again!");
            }
        });

        if (!driver) {
            toastr.error("There have been errors, please try again!")
            return;
        }
        //đổ dữ liệu vào form
        $('#driver-form #name').val(driver.name);
        $('#driver-form #address').val(driver.address);
        $('#driver-form #phone').val(driver.phone);
        $('#driver-form #level').val(driver.level);

        $('#driver-modal #save-driver-btn').attr('action-type', "UPDATE");
        $('#driver-modal #save-driver-btn').attr("driver-id", updateDriverId);
        $('#driver-modal').modal("show");
    });

    //create or update driver
    $('#save-driver-btn').click(function (event) {
        //validate
        const isValidForm = $('#driver-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const driverId = $(event.currentTarget).attr("driver-id");
        //lấy dữ liệu từ form
        const formData = $('#driver-form').serializeArray();
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
            requestBody["id"] = driverId;
        }

        function makeBasicAuthenticationHeader(user, password) {
            const hash = btoa(user + ':' + password);
            return "Basic " + hash;
        }

        //call api lên backend
        $.ajax({
            url: "/api/v1/drivers",
            type: method,
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', makeBasicAuthenticationHeader('admin', 'admin123'));
            },
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new driver successfully!");
                $(event.currentTarget).attr("action-type", "");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });
        $("#driver-modal #save-driver-btn").attr("action-type", "");
        $('#driver-modal #save-driver-btn').attr("driver-id", "");
    });

    //open modal delete driver
    $('.delete-driver-btn').click(function (event) {
        deleteDriverId = parseInt($(event.currentTarget).attr("driver-id"));
        $('#driver-delete-modal').modal('show');
    });

    //delete driver
    $('#delete-driver-btn').click(function () {
        $.ajax({
            url: "/api/v1/drivers/" + deleteDriverId,
            type: "DELETE",
            success: function (data) {
                toastr.success("You have already deleted a driver successfully!");
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
    $('#driver-modal').on('hidden.bs.modal', function () {
        $("#driver-modal #save-driver-btn").attr("action-type", "");
        $('#driver-modal #save-driver-btn').attr("driver-id", "");
        $('#driver-form').trigger("reset");
        $('#driver-form input').removeClass("error");
        validator.resetForm();
    });



});