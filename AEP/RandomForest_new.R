library("tidyverse")
library("readxl")
library("klaR")
library("randomForest")
library("caret")
library("tidymodels")
library("FactoMineR")
library("factoextra")
library("ranger")
require("caTools")

summary = read_xlsx("data/Summary.xlsx")
# State, Total_margin, Margin_per_invoice, Revenue, Cost, CDSID, invoice count
# Can't do zip because R's random forest library doesn't take with factors more the 53 levels deep, Zip is 542 levels
summary = subset(summary, select = -c(State, Total_margin, Margin_per_invoice, Revenue, Cost, CDSID, Invoice_count, Total_mwh, MWh_per_invoice))


summary <- transform(
  summary,
  Margin = as.factor(Margin),
  Zip = as.factor(Zip),
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

summary <- na.omit(summary)
famd <- FAMD(summary, ncp = 10, graph = T)
var <- get_famd_var(famd)
# Coordinates of variables
head(var$coord)
# Cos2: quality of representation on the factore map
head(var$cos2)
# Contributions to the  dimensions
head(var$contrib)

# Plot of variables
fviz_famd_var(famd, repel = TRUE)
# Contribution to the first dimension
fviz_contrib(famd, "var", axes = 1)
# Contribution to the second dimension
fviz_contrib(famd, "var", axes = 2)
fviz_contrib(famd, "var", axes = 3)

  #removed because hurt accuracy
##summary = subset(summary, select = c(Margin, Home_Est_Current_Value_Code, Zip, census_median_home_value, census_median_hh_income, household_net_worth, estimated_income_code, home_improvement_grouping, home_decor_enthusiast, cooking_enthusiast, computer_owner))

sample = sample.split(summary$Margin, SplitRatio = 0.75)
train = subset(summary, sample == TRUE)
test  = subset(summary, sample == FALSE)


####random forest####


rfr <-ranger(formula = Margin ~ .,
       data = train)
pred_rfr <- predict(rfr, data = test[-1])
confusionMatrix(predictions(pred_rfr), test[,1])
###
#  Confusion Matrix and Statistics
# 
# Reference
# Prediction Negative Positive
# Negative      156       50
# Positive       79      296
# 
# Accuracy : 0.778           
# 95% CI : (0.7419, 0.8111)
# No Information Rate : 0.5955          
# P-Value [Acc > NIR] : < 2e-16         
# 
# Kappa : 0.5298          
# 
# Mcnemar's Test P-Value : 0.01369         
#                                           
#             Sensitivity : 0.6638          
#             Specificity : 0.8555          
#          Pos Pred Value : 0.7573          
#          Neg Pred Value : 0.7893          
#              Prevalence : 0.4045          
#          Detection Rate : 0.2685          
#    Detection Prevalence : 0.3546          
#       Balanced Accuracy : 0.7597          
#                                           
#        'Positive' Class : Negative 
###
