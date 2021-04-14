library("tidyverse")
library("readxl")
library("klaR")
library("caret")
library("gbm")
require("caTools")

summary = read_xlsx("data/Summary.xlsx")
# State, Total_margin, Margin_per_invoice, Revenue, Cost, CDSID, invoice count
summary = subset(summary, select = -c(State, Margin, Margin_per_invoice, Revenue, Cost, CDSID, Invoice_count, Total_mwh, MWh_per_invoice))


summary <- transform(
  summary,
  dwelling_type = as.factor(dwelling_type),
  Zip = as.factor(Zip),
  homeowner_probability_model = as.factor(homeowner_probability_model),
  home_swimming_pool = as.factor(home_swimming_pool),
  home_air_conditioning =as.factor(home_air_conditioning),
  home_heating_system = as.factor(home_heating_system),
  home_sewer = as.factor(home_sewer),
  home_water = as.factor(home_water),
  census_median_home_value = as.integer(census_median_home_value),
  census_median_hh_income = as.integer(census_median_hh_income),
  Home_Year_Built = as.integer(Home_Year_Built),
  Home_Purchase_Price_Code  = as.integer(Home_Purchase_Price_Code),
  Home_Purchase_Year = as.integer(Home_Purchase_Year),
  Home_Est_Current_Value_Code = as.factor(Home_Est_Current_Value_Code),
  property_home_square_footage = as.integer(property_home_square_footage),
  property_land_square_footage = as.integer(property_land_square_footage),
  gender = as.factor(gender),
  dob_yr = as.integer(dob_yr),
  marital_status = as.factor(marital_status),
  occupation_group = as.factor(occupation_group),
  education_of_person = as.factor(education_of_person),
  single_parent = as.factor(single_parent),
  estimated_income_code = as.factor(estimated_income_code),
  household_net_worth = as.factor(household_net_worth),
  Secondary_Address_Present = as.factor(Secondary_Address_Present),
  presence_of_children = as.factor(presence_of_children),
  number_of_children = as.integer(number_of_children),
  consumer_electronics = as.factor(consumer_electronics),
  computer_owner = as.factor(computer_owner),
  cooking_enthusiast = as.factor(cooking_enthusiast),
  Pets_Multi = as.factor(Pets_Multi),
  gardening = as.factor(gardening),
  home_decor_enthusiast = as.factor(home_decor_enthusiast),
  home_improvement_grouping = as.factor(home_improvement_grouping),
  outdoor_enthusiast = as.factor(outdoor_enthusiast),
  Travel_Grouping = as.factor(Travel_Grouping)
)

sample = sample.split(summary$Total_margin, SplitRatio = 0.75)
train = subset(summary, sample == TRUE)
test  = subset(summary, sample == FALSE)



####GBM####
gbm <- gbm(formula = Total_margin ~ ., 
           distribution = "gaussian",
           data = train,
           n.trees = 200,
           interaction.depth = 3,
           shrinkage = 0.1,
           bag.fraction = 1,
           n.minobsinnode = 5,
           cv.folds = 5)
saveRDS(gbm, file = "gbmModel")

sqrt(min(gbm$cv.error))
gbm.perf(gbm, method = "cv")


summary(
  gbm, 
  cBars =10,
  method = relative.influence, # also can use permutation.test.gbm
  las = 2
)
###
# var     rel.inf
# Zip                                                   Zip 87.39166833
# density                                           density  3.66986334
# property_land_square_footage property_land_square_footage  2.17253713
# Home_Year_Built                           Home_Year_Built  1.47139765
# home_heating_system                   home_heating_system  1.45488783
# estimated_income_code               estimated_income_code  1.20079788
# Home_Est_Current_Value_Code   Home_Est_Current_Value_Code  0.69574054
# household_net_worth                   household_net_worth  0.55306608
# occupation_group                         occupation_group  0.34475496
# home_air_conditioning               home_air_conditioning  0.31038061
# home_swimming_pool                     home_swimming_pool  0.16656458
# number_of_children                     number_of_children  0.16222495
# census_median_hh_income           census_median_hh_income  0.14199272
# census_median_home_value         census_median_home_value  0.08720055
# Home_Purchase_Year                     Home_Purchase_Year  0.07554091
# Home_Purchase_Price_Code         Home_Purchase_Price_Code  0.05044009
# education_of_person                   education_of_person  0.02997317
# number_of_persons_in_unit       number_of_persons_in_unit  0.02096869
# dwelling_type                               dwelling_type  0.00000000
# homeowner_probability_model   homeowner_probability_model  0.00000000
# home_sewer                                     home_sewer  0.00000000
# home_water                                     home_water  0.00000000
# property_home_square_footage property_home_square_footage  0.00000000
# gender                                             gender  0.00000000
# dob_yr                                             dob_yr  0.00000000
# marital_status                             marital_status  0.00000000
# length_of_residence_code         length_of_residence_code  0.00000000
# single_parent                               single_parent  0.00000000
# Secondary_Address_Present       Secondary_Address_Present  0.00000000
# presence_of_children                 presence_of_children  0.00000000
# number_of_adults                         number_of_adults  0.00000000
# consumer_electronics                 consumer_electronics  0.00000000
# computer_owner                             computer_owner  0.00000000
# cooking_enthusiast                     cooking_enthusiast  0.00000000
# Pets_Multi                                     Pets_Multi  0.00000000
# gardening                                       gardening  0.00000000
# home_decor_enthusiast               home_decor_enthusiast  0.00000000
# home_improvement_grouping       home_improvement_grouping  0.00000000
# outdoor_enthusiast                     outdoor_enthusiast  0.00000000
# Travel_Grouping                           Travel_Grouping  0.00000000
###

pred_gbm <- predict(gbm , n.trees = gbm$n.trees, test)
RMSE(pred_gbm, test$Total_margin)
