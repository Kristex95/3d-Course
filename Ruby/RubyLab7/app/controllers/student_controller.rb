class StudentController < ApplicationController
  skip_before_action :verify_authenticity_token
  def index
    @students = Student.all
  end

  def new

  end

  def create
    Student.create(
      name: params[:name],
      surname: params[:surname]
    )
    redirect_to "/student"
  end

  def update
    @student = Student.find(params[:id])
    if @student.update(student_params)
      redirect_to "/student"
    else
      render "new"
    end
  end

  def read
  end

  def edit
    @student = Student.find(params[:id])
  end

  def destroy
    @student = Student.find(params[:id])
    @books = Book.all
    Book.destroy_by(student_id: @student.id)
    @student.destroy
    redirect_to "/student"
  end

  private def student_params
    params.require(:student).permit(:name, :surname)

  end
end
