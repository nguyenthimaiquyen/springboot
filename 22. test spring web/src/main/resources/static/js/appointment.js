$(document).ready(function () {
    toastr.options.timeOut = 2500; //2.5s

    let approveAppointmentId = -1;
    let rejectAppointmentId = -1;

    //open modal approve appointment
    $('.admin-appointment-approve-btn').click(function (event) {
        const status = $(event.currentTarget).attr("status");
        if (status !== "PENDING") {
            return;
        }
        approveAppointmentId = parseInt($(event.currentTarget).attr("appointment-id"));
        $('#admin-appointment-approvement-modal').modal('show');
    });

    //approve appointment
    $('#admin-approve-appointment-btn').click(function (event) {
        //gọi api để sửa trạng thái cuộc hẹn thành approved
        $.ajax({
            url: "/admin/appointments/" + approveAppointmentId,
            type: "PUT",
            data: JSON.stringify(null), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Approve a appointment successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });

        //reset id
        $('#student-modal #save-student-btn').attr("student-id", "");
    });

    //open modal reject appointment
    $('.admin-appointment-reject-btn').click(function (event) {
        const status = $(event.currentTarget).attr("status");
        if (status !== "PENDING") {
            return;
        }
        rejectAppointmentId = parseInt($(event.currentTarget).attr("appointment-id"));
        $('#admin-appointment-reject-modal').modal('show');
    });

    //reject appointment
    $('#admin-reject-appointment-btn').click(function (event) {
        $.ajax({
            url: "/admin/appointments/reject/" + rejectAppointmentId,
            type: "PUT",
            data: JSON.stringify(null), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Reject a appointment successfully!");
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