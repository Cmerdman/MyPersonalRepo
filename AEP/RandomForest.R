library("tidyverse")
library("readxl")
library("klaR")
library("randomForest")
library("caret")
require("caTools")

summary = read_xlsx("data/Summary.xlsx")
# State, Total_margin, Margin_per_invoice, Revenue, Cost, CDSID, invoice count
# Can't do zip because R's random forest library doesn't take with factors more the 53 levels deep, Zip is 542 levels
summary = subset(summary, select = -c(State, Total_margin, Margin_per_invoice, Revenue, Zip, Cost, CDSID, Invoice_count, Total_mwh, MWh_per_invoice))


summary <- transform(
  summary,
  Margin = as.factor(Margin),
  dwelling_type = as.factor(dwelling_type),
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
  
sample = sample.split(summary$Margin, SplitRatio = 0.75)
train = subset(summary, sample == TRUE)
test  = subset(summary, sample == FALSE)

####random forest####
rf <- randomForest(
  formula=Margin ~ .,
  data=train,
  na.action=na.omit)
saveRDS(rf, file = "randomForestModel")
varImpPlot(rf)

pred_rf <- predict(rf, newdata = test[-1])
confusionMatrix(pred_rf, test[,1])
###
# Confusion Matrix and Statistics
# 
# Reference
# Prediction Negative Positive
# Negative       97       39
# Positive      144      299
# 
# Accuracy : 0.6839          
# 95% CI : (0.6443, 0.7217)
# No Information Rate : 0.5838          
# P-Value [Acc > NIR] : 4.338e-07       
# 
# Kappa : 0.3063          
# 
# Mcnemar's Test P-Value : 1.496e-14       
#                                           
#             Sensitivity : 0.4025          
#             Specificity : 0.8846          
#          Pos Pred Value : 0.7132          
#          Neg Pred Value : 0.6749          
#              Prevalence : 0.4162          
#          Detection Rate : 0.1675          
#    Detection Prevalence : 0.2349          
#       Balanced Accuracy : 0.6436          
#                                           
#        'Positive' Class : Negative
###
