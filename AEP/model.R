library("tidyverse")
library("readxl")
library("randomForest")
require("caTools")

invoices = read_xlsx("data/Invoices.xlsx")
summary = read_xlsx("data/Summary.xlsx")
income = read.csv("data/income_data.csv")
income <- income[,-c(1)]
annIncome <- read.csv("data/annualIncomeSummary.csv")


summary <- transform(
  summary,
  CDSID = as.factor(CDSID),
  margin = as.factor(margin),
  state = as.factor(state),
  zip = as.factor(zip),
  dwelling_type = as.factor(dwelling_type),
  homeowner_probability_model = as.factor(homeowner_probability_model),
  home_swimming_pool = as.factor(home_swimming_pool),
  home_air_conditioning =as.factor(home_air_conditioning),
  home_heating_system = as.factor(home_heating_system),
  home_sewer = as.factor(home_sewer),
  home_water = as.factor(home_water),
  census_median_home_value = as.integer(census_median_home_value),
  census_median_hh_income = as.integer(census_median_hh_income),
  Home_Purchase_Price_Code  = as.factor(Home_Purchase_Price_Code),
  Home_Est_Current_Value_Code = as.factor(Home_Est_Current_Value_Code),
  density = as.integer(density),
  property_home_square_footage = as.integer(property_home_square_footage),
  property_land_square_footage = as.integer(property_land_square_footage),
  gender = as.factor(gender),
  marital_status = as.factor(marital_status),
  occupation_group = as.factor(occupation_group),
  education_of_person = as.factor(education_of_person),
  single_parent = as.factor(single_parent),
  estimated_income_code = as.factor(estimated_income_code),
  household_net_worth = as.fcator(household_net_worth),
  Secondary_Address_Present = as.factor(Secondary_Address_Present),
  presence_of_children = as.factor(presence_of_children),
  number_of_children = as.integer(number_of_children),
  
 )
