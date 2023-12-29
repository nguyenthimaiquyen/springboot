$(document).ready(() => {
    toastr.options.timeOut = 2500; // 2.5s

    //call api lấy thông tin của tài xế
    $.ajax({
        url: "/api/v1/assignments/drivers",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const assignmentSelection = $('#assignment-modal #driverId');
            if (assignmentSelection.children().length === 0) {
                if (!data || data.length === 0) {
                    return;
                }
                let assignmentOptions = "";
                for (let i = 0; i < data.length; i++) {
                    assignmentOptions += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
                assignmentSelection.append($(assignmentOptions));
            }
        },
        error: function (data) {
            toastr.warning(data.responseJSON.error);
        }
    });

    //call api lấy thông tin của tuyến xe
    $.ajax({
        url: "/api/v1/assignments/buses",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const busSelection = $('#assignment-modal #busId');
            if (busSelection.children().length === 0) {
                if (!data || data.length === 0) {
                    return;
                }
                let busOptions = "";
                for (let i = 0; i < data.length; i++) {
                    busOptions += "<option value='" + data[i].id + "'>" + data[i].id + "</option>";
                }
                busSelection.append($(busOptions));
            }
        },
        error: function (data) {
            toastr.warning(data.responseJSON.error);
        }
    });

    //validate
    const validator = $("#assignment-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "driver": {
                required: true,
            },
            "bus": {
                required: true,
            },
            "driving": {
                required: true,
                min: 1
            },
            "assignmentTime": {
                required: true,
                min: 1
            }
        },
        messages: {
            "driver": {
                required: "Driver is required",
            },
            "bus": {
                required: "Bus is required",
            },
            "driving": {
                required: "Driving is required",
                min: "Driving must be greater than 1"
            },
            "assignmentTime": {
                required: "Assignment time is required",
                min: "Assignment time must be greater than 1"
            }
        }
    });

    //mở modal tạo mới lái xe
    $('.create-assignment-btn').click(function () {
        $('#assignment-modal #save-assignment-btn').attr("action-type", "CREATE");
        $('#assignment-modal').modal('show');
    });

    //open modal to update a assignment
    $('.update-assignment-btn').click(async function (event) {
        //call api lên java và lấy dữ liệu
        console.log("vào hàm rồi")
        const updateAssignmentId = parseInt($(event.currentTarget).attr("assignment-id"));
        let assignment = null;
        await $.ajax({
            url: "/api/v1/assignments/" + updateAssignmentId,
            type: "GET",
            success: function (data) {
                assignment = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("There have been errors, please try again!");
            }
        });
        console.log(assignment)

        if (!assignment) {
            toastr.error("There have been errors, please try again!")
            return;
        }
        //đổ dữ liệu vào form
        $('#assignment-form #driver').val(assignment.driver);
        $('#assignment-form #bus').val(assignment.bus);
        $('#assignment-form #driving').val(assignment.driving);
        $('#assignment-form #assignmentTime').val(assignment.assignmentTime);

        $('#assignment-modal #save-assignment-btn').attr('action-type', "UPDATE");
        $('#assignment-modal #save-assignment-btn').attr("assignment-id", updateAssignmentId);
        $('#assignment-modal').modal("show");
    });

    //create or update assignment
    $('#save-assignment-btn').click(function (event) {
        //validate
        const isValidForm = $('#assignment-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const assignmentId = $(event.currentTarget).attr("assignment-id");
        //lấy dữ liệu từ form
        const formData = $('#assignment-form').serializeArray();
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
            requestBody["id"] = assignmentId;
        }

        function makeBasicAuthenticationHeader(user, password) {
            const hash = btoa(user + ':' + password);
            return "Basic " + hash;
        }

        //call api lên backend
        $.ajax({
            url: "/api/v1/assignments",
            type: method,
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', makeBasicAuthenticationHeader('admin', 'admin123'));
            },
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new assignment successfully!");
                $(event.currentTarget).attr("action-type", "");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });
        $("#assignment-modal #save-assignment-btn").attr("action-type", "");
        $('#assignment-modal #save-assignment-btn').attr("assignment-id", "");
    });

    //reset form
    $('#assignment-modal').on('hidden.bs.modal', function () {
        $("#assignment-modal #save-assignment-btn").attr("action-type", "");
        $('#assignment-modal #save-assignment-btn').attr("assignment-id", "");
        $('#assignment-form').trigger("reset");
        $('#assignment-form input').removeClass("error");
        validator.resetForm();
    });


});