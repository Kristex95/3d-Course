class BookController < ApplicationController
  skip_before_action :verify_authenticity_token
  def index
    @books = Book.all
  end

  def new
    @students = Student.all
  end

  def create
    Book.create(
      name: params[:book][:name],
      issue_date: params[:book][:issue_date].to_s,
      order_date: params[:book][:order_date].to_s,
      student_id: params[:student_id]
    )
    redirect_to home_path
  end

  def edit
    @book = Book.find(params[:id])
    @students = Student.all
  end

  def update
    @book = Book.find(params[:id])
    if @book.update(name: params[:book][:name],
                    issue_date: params[:book][:issue_date].to_s,
                    order_date: params[:book][:order_date].to_s,
                    student_id: params[:student_id])
      redirect_to "/book"
    else
      render "new"
    end
  end

  def destroy
    @book = Book.find(params[:id])
    @book.destroy
    redirect_to "/book"
  end

  private def book_params
    params.require(:book).permit(:name, :issue_date, :order_date, :student_id)

  end
end
