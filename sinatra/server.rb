require 'rubygems'
require 'sinatra'

get '/cctray.xml' do
  content_type "application/xml", :charset => "utf-8"
  File.open("mocks/cctray.xml", "rb").read
end
