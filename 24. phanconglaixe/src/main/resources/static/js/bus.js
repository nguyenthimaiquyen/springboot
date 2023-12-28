$(document).ready(() => {
    toastr.options.timeOut = 2500; // 2.5s

    let deleteBusId = -1;

    //validate
    const validator = $("#bus-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "distance": {
                required: true,
                min: 1
            },
            "busStop": {
                required: true,
                min: 1
            }
        },
        messages: {
            "distance": {
                required: "Distance is required",
                min: "Distance must be greater than 1"
            },
            "busStop": {
                required: "Bus Stop is required",
                min: "Bus Stop must be greater than 1"
            }
        }
    });

    //mở modal tạo mới lái xe
    $('.create-bus-btn').click(function () {
        $('#bus-modal #save-bus-btn').attr("action-type", "CREATE");
        $('#bus-modal').modal('show');
    });

    //open modal to update a bus
    $('.update-bus-btn').click(async function (event) {
        //call api lên java và lấy dữ liệu
        const updateBusId = parseInt($(event.currentTarget).attr("bus-id"));
        let bus = null;
        await $.ajax({
            url: "/api/v1/buses/" + updateBusId,
            type: "GET",
            success: function (data) {
                bus = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("There have been errors, please try again!");
            }
        });

        if (!bus) {
            toastr.error("There have been errors, please try again!")
            return;
        }
        //đổ dữ liệu vào form
        $('#bus-form #distance').val(bus.distance);
        $('#bus-form #busStop').val(bus.busStop);

        $('#bus-modal #save-bus-btn').attr('action-type', "UPDATE");
        $('#bus-modal #save-bus-btn').attr("bus-id", updateBusId);
        $('#bus-modal').modal("show");
    });

    //create or update bus
    $('#save-bus-btn').click(function (event) {
        //validate
        const isValidForm = $('#bus-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const busId = $(event.currentTarget).attr("bus-id");
        //lấy dữ liệu từ form
        const formData = $('#bus-form').serializeArray();
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
            requestBody["id"] = busId;
        }

        function makeBasicAuthenticationHeader(user, password) {
            const hash = btoa(user + ':' + password);
            return "Basic " + hash;
        }

        //call api lên backend
        $.ajax({
            url: "/api/v1/buses",
            type: method,
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', makeBasicAuthenticationHeader('admin', 'admin123'));
            },
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new bus successfully!");
                $(event.currentTarget).attr("action-type", "");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });
        $("#bus-modal #save-bus-btn").attr("action-type", "");
        $('#bus-modal #save-bus-btn').attr("bus-id", "");
    });

    //open modal delete bus
    $('.delete-bus-btn').click(function (event) {
        deleteBusId = parseInt($(event.currentTarget).attr("bus-id"));
        $('#bus-delete-modal').modal('show');
    });

    //delete bus
    $('#delete-bus-btn').click(function () {
        $.ajax({
            url: "/api/v1/buses/" + deleteBusId,
            type: "DELETE",
            success: function (data) {
                toastr.success("You have already deleted a bus successfully!");
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
    $('#bus-modal').on('hidden.bs.modal', function () {
        $("#bus-modal #save-bus-btn").attr("action-type", "");
        $('#bus-modal #save-bus-btn').attr("bus-id", "");
        $('#bus-form').trigger("reset");
        $('#bus-form input').removeClass("error");
        validator.resetForm();
    });



});