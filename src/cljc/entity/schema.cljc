(ns entity.schema
  (:require [malli.core :as ml]))

(def CountryCode
  [:and {:title "Country Code"} string?])

(def Country
  [:map
   [:country-code CountryCode]
   [:name string?]])


(def ContinentName
  [:and {:title "Continent Name"} string?])

(def Continent
  [:and
   ;TODO : think if i can move this at the field - may not work as loader may require more than one field
   {:field-resolvers {:loader-id :country-by-id
                      :params    [{:param-id :continent
                                   :path     [:name]}]}}
   [:map
    [:name ContinentName]
    [:countries [:sequential Country]]]])

(def Location
  [:map
   [:name string?]])

(def entity-loaders
  {:continents          {:returns     [:sequential Continent]
                         :description "All continents"}
   :country-cities      {:returns Location
                         :params  [:map [:country-code  CountryCode]]}
   :continent-countries {:returns     [:sequential Country]
                         :description "Countries For Continent"
                         :params      [:map
                                       [:continent ContinentName]]}})

(defn root-loaders [loaders]
  (->> (filter (fn [[_ loader-spec]]
                 (:params loader-spec)) loaders)
       (into {})))

(defn loader-for-type [field-type])

;TODO: trying to get rid of this moving at the schema definition
(def entity-specs
  [{:entity-id       "continents"
    :entity-schema   [:sequential Continent]
    :field-resolvers [{:loader-id :country-by-id
                       :params    [{:param-id  :continent
                                    :path-spec [[] :name]}]}]}
   {:entity-id     "countries"
    :entity-schema [:sequential Country]}])






(defn test-simple []
  (ml/validate Country {:id   "ro"
                        :name "Romania"}))

(defn test-collection []
  (ml/validate Continent {:name      "Europe"
                          :countries [{:id   "ro"
                                       :name "Romania"}
                                      {:id   "md"
                                       :name "Moldova"}]}))