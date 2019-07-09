(ns modelion-spa.ui-components.commons
  (:require
   ["@material-ui/core" :as mui]))


(defn ModelElement
  [{:keys [element-type
           element-name
           content]}]
  [:> mui/Card
   [:> mui/CardHeader
    {:title (if element-type
              (str element-type ": " element-name)
              (str element-name))}]
   [:> mui/CardContent
    content]])


(defn ModelElement-List
  [{:keys [title
           elements]}]
  [:div
   [:h4 title]
   (into
    [:div
     {:style {:display :flex
              :flex-direction :column
              :background-color :green}}]
    (map
     (fn [element]
       [:div
        {:style {:margin "5px 0"}}
        element])
     elements))])
